package com.pashynski.forum.feature.topic;

import com.pashynski.forum.common.DuplicateTopicNameException;
import com.pashynski.forum.feature.category.CategoryEntity;
import com.pashynski.forum.feature.topic.dto.SaveTopicDto;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import com.pashynski.forum.feature.user.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final EntityManager em;

    public Page<TopicDto> getAllTopicsByCategory(UUID categoryId, Pageable pageable) {
        Page<TopicEntity> topicPage = topicRepository.findAllByCategoryId(categoryId, pageable);
        return topicPage.map(topicMapper::toDto);
    }

    public TopicDto saveTopic(SaveTopicDto saveTopicDto, UUID createdBy) {
        try {
            TopicEntity topicEntity = new TopicEntity();
            topicEntity.setName(saveTopicDto.name());
            topicEntity.setCategory(em.getReference(CategoryEntity.class, saveTopicDto.categoryId()));
            topicEntity.setCreatedBy(em.getReference(UserEntity.class, createdBy));
            TopicEntity savedEntity = topicRepository.saveAndFlush(topicEntity);
            return topicMapper.toDto(savedEntity);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException cve
                    && cve.getConstraintName().equals("topics_name_key")) {
                throw new DuplicateTopicNameException("Topic with name '" + saveTopicDto.name() + "' already exists");
            }
            throw e;
        }
    }

    @Retryable(
            value = OptimisticLockException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 20)
    )
    public void incrementPostsCounter(UUID topicId) {
        TopicEntity topicEntity = topicRepository.getReferenceById(topicId);
        topicEntity.incrementPostsCount();
    }
}
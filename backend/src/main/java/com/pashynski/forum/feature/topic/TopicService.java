package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.topic.dto.TopicDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public Page<TopicDto> getAllTopicsByCategory(UUID categoryId, Pageable pageable) {
        Page<TopicEntity> topicPage = topicRepository.findAllByCategoryId(categoryId, pageable);
        return topicPage.map(topicMapper::toDto);
    }
}
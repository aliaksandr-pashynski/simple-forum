package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.topic.dto.TopicDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public List<TopicDto> getAllTopicsByCategory(UUID categoryId) {
        List<TopicEntity> topicList = topicRepository.findAllByCategoryId(categoryId);
        return topicList.stream().map(topicMapper::toDto).toList();
    }
}
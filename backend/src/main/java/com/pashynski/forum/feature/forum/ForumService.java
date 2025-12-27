package com.pashynski.forum.feature.forum;

import com.pashynski.forum.feature.category.CategoryService;
import com.pashynski.forum.feature.topic.TopicService;
import com.pashynski.forum.feature.topic.dto.SaveTopicDto;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class ForumService {

    private final CategoryService categoryService;
    private final TopicService topicService;

    public TopicDto saveTopic(SaveTopicDto saveTopicDto, UUID createdBy) {
        TopicDto topicDto = topicService.saveTopic(saveTopicDto, createdBy);
        categoryService.incrementTopicsCounter(saveTopicDto.categoryId());
        return topicDto;
    }
}
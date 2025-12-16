package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.topic.dto.GetAllTopicsResponse;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/topics")
    public ResponseEntity<?> getTopics(@RequestParam UUID categoryId) {
        List<TopicDto> allTopics = topicService.getAllTopicsByCategory(categoryId);
        return ResponseEntity.ok(new GetAllTopicsResponse(allTopics, allTopics.size()));
    }
}
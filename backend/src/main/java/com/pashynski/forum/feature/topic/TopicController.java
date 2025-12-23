package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.topic.dto.TopicDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class TopicController {

    private final TopicService topicService;
    private final int topicsMaxPageSize;

    public TopicController(
            TopicService topicService,
            @Value("${topics.max-page-size}") int topicsMaxPageSize
    ) {
        this.topicService = topicService;
        this.topicsMaxPageSize = topicsMaxPageSize;
    }

    @GetMapping("/topics")
    public ResponseEntity<?> getTopics(
            @RequestParam UUID categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, Math.min(size, topicsMaxPageSize));
        Page<TopicDto> topicPage = topicService.getAllTopicsByCategory(categoryId, pageable);
        return ResponseEntity.ok(topicPage);
    }
}
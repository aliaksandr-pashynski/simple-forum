package com.pashynski.search.controller;

import com.pashynski.search.documents.Topic;
import com.pashynski.search.dto.SearchTopicRequest;
import com.pashynski.search.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchTopicController {

    private final TopicService topicService;

    @PostMapping("/topics/search")
    public ResponseEntity<?> searchTopicName(@RequestBody SearchTopicRequest request) {
        Page<Topic> topics = topicService.searchTopics(request);
        return ResponseEntity.ok(topics);
    }
}
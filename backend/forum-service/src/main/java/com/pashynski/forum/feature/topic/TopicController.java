package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.forum.ForumService;
import com.pashynski.forum.feature.topic.TopicService;
import com.pashynski.forum.feature.topic.dto.SaveTopicDto;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class TopicController {

    private final TopicService topicService;
    private final ForumService forumService;
    private final int topicsMaxPageSize;

    public TopicController(
            TopicService topicService,
            ForumService forumService,
            @Value("${topics.max-page-size}") int topicsMaxPageSize
    ) {
        this.topicService = topicService;
        this.forumService = forumService;
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

    @PostMapping("/topics")
    public ResponseEntity<?> saveTopic(
            @Valid @RequestBody SaveTopicDto saveTopicDto,
            @AuthenticationPrincipal Jwt token
    ) {
        TopicDto topicDto = forumService.saveTopic(saveTopicDto, UUID.fromString(token.getSubject()));
        return ResponseEntity.ok(topicDto);
    }
}
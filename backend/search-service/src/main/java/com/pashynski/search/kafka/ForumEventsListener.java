package com.pashynski.search.kafka;

import com.pashynski.search.service.PostService;
import com.pashynski.search.service.TopicService;
import com.pashynski.shared.kafka.PostCreated;
import com.pashynski.shared.kafka.TopicCreated;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@KafkaListener(topics = "forum-dev", containerPostProcessor = "listenerPostProcessor")
public class ForumEventsListener {

    private final TopicService topicService;
    private final PostService postService;

    @KafkaHandler
    public void handleTopicCreated(TopicCreated topicCreatedMessage) {
        topicService.indexTopic(topicCreatedMessage);
    }

    @KafkaHandler
    public void handlePostCreated(PostCreated postCreatedMessage) {
        postService.indexPost(postCreatedMessage);
    }
}
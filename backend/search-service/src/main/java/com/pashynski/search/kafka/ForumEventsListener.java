package com.pashynski.search.kafka;

import com.pashynski.shared.kafka.PostCreated;
import com.pashynski.shared.kafka.TopicCreated;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "forum-dev", containerPostProcessor = "listenerPostProcessor")
public class ForumEventsListener {

    @KafkaHandler
    public void handleTopicCreated(TopicCreated topicCreatedMessage) {
        //todo implement elastic indexing
    }

    @KafkaHandler
    public void handlePostCreated(PostCreated postCreatedMessage) {
        //todo implement elastic indexing
    }
}

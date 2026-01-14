package com.pashynski.forum.feature.kafka;

import com.pashynski.forum.feature.topic.dto.SaveTopicDto;
import com.pashynski.shared.kafka.TopicCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void topicCreated(SaveTopicDto topicDto, UUID createdBy) {
        TopicCreatedMessage payload = new TopicCreatedMessage(topicDto.name(), topicDto.categoryId(), createdBy);
        Message<TopicCreatedMessage> message = MessageBuilder.withPayload(payload).build();
        //async
        kafkaTemplate.send(message);
    }
}
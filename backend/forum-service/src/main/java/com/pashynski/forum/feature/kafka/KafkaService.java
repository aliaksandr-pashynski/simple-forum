package com.pashynski.forum.feature.kafka;

import com.pashynski.forum.feature.post.PostMapper;
import com.pashynski.forum.feature.post.dto.PostDto;
import com.pashynski.forum.feature.topic.TopicMapper;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import com.pashynski.shared.kafka.PostCreated;
import com.pashynski.shared.kafka.TopicCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    public void topicCreated(TopicDto topicDto) {
        Message<TopicCreated> message = MessageBuilder.withPayload(topicMapper.toKafkaMessage(topicDto)).build();
        //async
        kafkaTemplate.send(message);
    }

    public void postCreated(PostDto postDto) {
        Message<PostCreated> message = MessageBuilder.withPayload(postMapper.toKafkaMessage(postDto)).build();
        //async
        kafkaTemplate.send(message);
    }
}
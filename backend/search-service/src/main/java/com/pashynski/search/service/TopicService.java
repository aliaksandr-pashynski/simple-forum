package com.pashynski.search.service;

import com.pashynski.search.documents.Topic;
import com.pashynski.search.dto.SearchTopicRequest;
import com.pashynski.search.mapper.TopicMapper;
import com.pashynski.search.repository.TopicRepository;
import com.pashynski.shared.kafka.TopicCreated;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public void indexTopic(TopicCreated kafkaMessage) {
        Topic document = topicMapper.toElasticDocument(kafkaMessage);
        topicRepository.save(document);
    }

    public Page<Topic> searchTopics(SearchTopicRequest request) {
        return topicRepository.findByNameMatches(request.query(), Pageable.ofSize(10));
    }
}
package com.pashynski.search.mapper;

import com.pashynski.search.documents.Topic;
import com.pashynski.shared.kafka.TopicCreated;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    Topic toElasticDocument(TopicCreated kafkaMessage);
}
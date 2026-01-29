package com.pashynski.search.mapper;

import com.pashynski.search.documents.Post;
import com.pashynski.shared.kafka.PostCreated;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    Post toElasticDocument(PostCreated kafkaMessage);
}
package com.pashynski.forum.feature.post;

import com.pashynski.forum.feature.post.dto.PostDto;
import com.pashynski.shared.kafka.PostCreated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    @Mapping(target = "topicId", source = "topic.id")
    PostDto toDto(PostEntity entity);

    PostCreated toKafkaMessage(PostDto postDto);
}
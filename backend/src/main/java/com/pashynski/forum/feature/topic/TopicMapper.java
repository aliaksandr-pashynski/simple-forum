package com.pashynski.forum.feature.topic;

import com.pashynski.forum.feature.topic.dto.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper {

    @Mapping(target = "categoryId", source = "category.id")
    TopicDto toDto(TopicEntity entity);
}
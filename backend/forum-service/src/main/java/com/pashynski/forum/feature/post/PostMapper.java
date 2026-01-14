package com.pashynski.forum.feature.post;

import com.pashynski.forum.feature.post.PostEntity;
import com.pashynski.forum.feature.post.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    PostDto toDto(PostEntity entity);
}
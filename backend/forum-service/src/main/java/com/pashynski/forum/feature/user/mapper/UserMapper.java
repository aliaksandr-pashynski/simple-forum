package com.pashynski.forum.feature.user.mapper;

import com.pashynski.forum.feature.user.UserEntity;
import com.pashynski.forum.feature.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDto toUserDto(UserEntity entity);
}
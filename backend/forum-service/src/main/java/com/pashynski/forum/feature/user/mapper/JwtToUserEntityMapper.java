package com.pashynski.forum.feature.user.mapper;

import com.pashynski.forum.feature.user.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtToUserEntityMapper {
    UserEntity toEntity(Jwt token);
}
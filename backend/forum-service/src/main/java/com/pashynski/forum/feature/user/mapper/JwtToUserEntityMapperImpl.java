package com.pashynski.forum.feature.user.mapper;

import com.pashynski.forum.feature.user.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtToUserEntityMapperImpl implements JwtToUserEntityMapper {

    @Override
    public UserEntity toEntity(Jwt token) {
        UUID id = UUID.fromString(token.getSubject());
        String email = (String) token.getClaims().get("email");
        String preferredUsername = (String) token.getClaims().get("preferred_username");
        if (preferredUsername == null) {
            preferredUsername = email;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setEmail(email);
        userEntity.setUsername(preferredUsername);
        
        return userEntity;
    }
}
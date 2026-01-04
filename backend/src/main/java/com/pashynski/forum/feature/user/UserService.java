package com.pashynski.forum.feature.user;

import com.pashynski.forum.feature.user.dto.UserDto;
import com.pashynski.forum.feature.user.mapper.JwtToUserEntityMapper;
import com.pashynski.forum.feature.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtToUserEntityMapper jwtToUserEntityMapper;

    public Optional<UserDto> findById(UUID userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.map(userMapper::toUserDto);
    }

    public UserDto saveUserFromJwtToken(Jwt token) {
        UserEntity userEntity = jwtToUserEntityMapper.toEntity(token);
        UserEntity saved = userRepository.saveAndFlush(userEntity);
        return userMapper.toUserDto(saved);
    }

    public UserDto updateAvatar(UUID userId, String avatar) {
        UserEntity userEntity = userRepository.getReferenceById(userId);
        userEntity.setAvatar(avatar);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.toUserDto(savedUser);
    }
}
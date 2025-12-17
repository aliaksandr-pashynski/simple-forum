package com.pashynski.forum.feature.user;

import com.pashynski.forum.feature.user.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto findById(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserDto(userEntity);
    }
}
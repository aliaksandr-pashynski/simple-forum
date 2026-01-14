package com.pashynski.forum.feature.user.dto;

import java.time.Instant;
import java.util.UUID;

public record UserDto(
        UUID id,
        String username,
        String email,
        long postsCreated,
        long topicsCreated,
        Instant registeredA,
        String avatar
) {
}
package com.pashynski.forum.feature.user.dto;

import java.time.Instant;
import java.util.UUID;

public record CreatedByDto(
        UUID id,
        String username,
        String avatar,
        Instant registeredAt
) {
}
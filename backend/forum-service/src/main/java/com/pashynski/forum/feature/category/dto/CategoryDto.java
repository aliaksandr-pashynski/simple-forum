package com.pashynski.forum.feature.category.dto;

import java.time.Instant;
import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        String description,
        long topics,
        long posts,
        Instant createdAt
) {
}
package com.pashynski.forum.feature.topic.dto;

import java.time.Instant;
import java.util.UUID;

public record TopicDto(
        UUID id,
        String name,
        long postsCount,
        Instant createdAt,
        UUID categoryId
) {
}
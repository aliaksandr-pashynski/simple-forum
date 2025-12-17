package com.pashynski.forum.feature.topic.dto;

import com.pashynski.forum.feature.user.dto.CreatedByDto;

import java.time.Instant;
import java.util.UUID;

public record TopicDto(
        UUID id,
        String name,
        long postsCount,
        Instant createdAt,
        UUID categoryId,
        CreatedByDto createdBy
) {
}
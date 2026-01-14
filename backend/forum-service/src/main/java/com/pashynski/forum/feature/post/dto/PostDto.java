package com.pashynski.forum.feature.post.dto;

import com.pashynski.forum.feature.user.dto.CreatedByDto;

import java.time.Instant;
import java.util.UUID;

public record PostDto(
        UUID id,
        String body,
        Instant createdAt,
        CreatedByDto createdBy,
        UUID topicId
) {
}
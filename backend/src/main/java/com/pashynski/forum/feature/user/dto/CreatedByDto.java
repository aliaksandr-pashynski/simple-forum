package com.pashynski.forum.feature.user.dto;

import java.util.UUID;

public record CreatedByDto(
        UUID id,
        String username
) {
}
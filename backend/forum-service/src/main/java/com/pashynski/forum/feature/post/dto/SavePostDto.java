package com.pashynski.forum.feature.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SavePostDto(
        @NotBlank(message = "Post body must not be empty")
        String body,

        @NotNull(message = "Topic id must not be empty")
        UUID topicId
) {
}
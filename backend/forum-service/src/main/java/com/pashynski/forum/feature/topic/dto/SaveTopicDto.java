package com.pashynski.forum.feature.topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SaveTopicDto(
        @NotBlank(message = "Topic name must not be empty")
        @Size(min = 3, max = 255, message = "Topic name must be between 3 and 255 characters")
        String name,

        @NotNull(message = "Category id must not be null")
        UUID categoryId
) {
}
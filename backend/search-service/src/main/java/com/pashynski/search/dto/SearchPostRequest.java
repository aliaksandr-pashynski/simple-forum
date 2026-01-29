package com.pashynski.search.dto;

import java.util.UUID;

public record SearchPostRequest(
        String query,
        UUID topicId
) {
}
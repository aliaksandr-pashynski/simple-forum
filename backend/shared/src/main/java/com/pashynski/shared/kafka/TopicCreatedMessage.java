package com.pashynski.shared.kafka;

import java.util.UUID;

public record TopicCreatedMessage(
        String name,
        UUID categoryId,
        UUID createdBy) {
}
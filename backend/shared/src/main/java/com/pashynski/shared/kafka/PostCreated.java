package com.pashynski.shared.kafka;

import java.util.UUID;

public record PostCreated(
        UUID id,
        String body,
        UUID topicId
) {
}
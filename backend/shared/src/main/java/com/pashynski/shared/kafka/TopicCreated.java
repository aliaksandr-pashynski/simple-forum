package com.pashynski.shared.kafka;

import java.util.UUID;

public record TopicCreated(
        UUID id,
        String name
) {
}
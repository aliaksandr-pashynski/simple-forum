package com.pashynski.shared.kafka;

import java.util.UUID;

public record PostCreatedMessage(
        UUID id,
        String body
) {
}

package com.pashynski.forum.feature.file;

import lombok.Builder;

@Builder
public record FileInfo(
        String bucket,
        String objectName,
        long sizeBytes,
        String contentType
) {
}
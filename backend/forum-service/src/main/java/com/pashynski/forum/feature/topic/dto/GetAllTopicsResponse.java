package com.pashynski.forum.feature.topic.dto;

import java.util.List;

public record GetAllTopicsResponse(
        List<TopicDto> topics,
        int total
) {
}
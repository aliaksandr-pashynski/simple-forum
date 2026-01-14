package com.pashynski.forum.feature.category.dto;

import java.util.List;

public record GetAllCategoriesResponse(
        List<CategoryDto> categories,
        int total
) {
}
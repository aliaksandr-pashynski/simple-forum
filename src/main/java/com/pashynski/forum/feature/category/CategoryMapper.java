package com.pashynski.forum.feature.category;

import com.pashynski.forum.feature.category.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryDto toDto(CategoryEntity entity);
}
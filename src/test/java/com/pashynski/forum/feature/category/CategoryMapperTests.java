package com.pashynski.forum.feature.category;

import com.pashynski.forum.feature.category.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class CategoryMapperTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("Smoke test for CategoryEntity to CategoryDto mapping")
    public void categoryMapperSmokeTest() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(UUID.randomUUID());
        categoryEntity.setName("Test Category 1");
        categoryEntity.setDescription("Some description");
        categoryEntity.setTopics(new Random().nextLong());
        categoryEntity.setPosts(new Random().nextLong());
        categoryEntity.setCreatedAt(Instant.now());

        CategoryDto categoryDto = categoryMapper.toDto(categoryEntity);

        Assertions.assertEquals(categoryEntity.getId(), categoryDto.id());
        Assertions.assertEquals(categoryEntity.getName(), categoryDto.name());
        Assertions.assertEquals(categoryEntity.getDescription(), categoryDto.description());
        Assertions.assertEquals(categoryEntity.getTopics(), categoryDto.topics());
        Assertions.assertEquals(categoryEntity.getPosts(), categoryDto.posts());
        Assertions.assertEquals(categoryEntity.getCreatedAt(), categoryDto.createdAt());
    }
}
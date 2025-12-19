package com.pashynski.forum.feature.category;

import com.pashynski.forum.feature.category.dto.CategoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("Should return mapped list of CategoryDto")
    void shouldReturnMappedList() {
        CategoryEntity category_1 = new CategoryEntity();
        category_1.setId(UUID.randomUUID());
        category_1.setName("Test Category 1");
        category_1.setDescription("Some description 1");
        category_1.setTopics(new Random().nextLong());
        category_1.setPosts(new Random().nextInt());
        category_1.setCreatedAt(Instant.now().minus(new Random().nextInt(), ChronoUnit.HOURS));

        CategoryDto categoryDto_1 = new CategoryDto(
                category_1.getId(),
                category_1.getName(),
                category_1.getDescription(),
                category_1.getTopics(),
                category_1.getPosts(),
                category_1.getCreatedAt()
        );

        CategoryEntity category_2 = new CategoryEntity();
        category_2.setId(UUID.randomUUID());
        category_2.setName("Test Category 2");
        category_2.setDescription("Some description 2");
        category_2.setTopics(new Random().nextLong());
        category_2.setPosts(new Random().nextInt());
        category_2.setCreatedAt(Instant.now().minus(new Random().nextInt(), ChronoUnit.HOURS));

        CategoryDto categoryDto_2 = new CategoryDto(
                category_2.getId(),
                category_2.getName(),
                category_2.getDescription(),
                category_2.getTopics(),
                category_2.getPosts(),
                category_2.getCreatedAt()
        );

        List<CategoryEntity> entities = List.of(category_1, category_2);

        when(categoryRepository.findAll()).thenReturn(entities);
        when(categoryMapper.toDto(category_1)).thenReturn(categoryDto_1);
        when(categoryMapper.toDto(category_2)).thenReturn(categoryDto_2);

        List<CategoryDto> result = categoryService.getCategories();

        assertThat(result).hasSize(2);
        assertThat(categoryDto_1).usingRecursiveComparison().isEqualTo(result.get(0));
        assertThat(categoryDto_2).usingRecursiveComparison().isEqualTo(result.get(1));

        verify(categoryRepository).findAll();
        verify(categoryMapper).toDto(category_1);
        verify(categoryMapper).toDto(category_2);
        verifyNoMoreInteractions(categoryRepository, categoryMapper);
        verify(categoryMapper, times(2)).toDto(any());

    }

    @Test
    @DisplayName("Should return empty list if no results")
    void shouldReturnEmptyListWhenNothingFound() {
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<CategoryDto> result = categoryService.getCategories();

        assertThat(result).isEmpty();
        verify(categoryMapper, times(0)).toDto(any());
    }

}
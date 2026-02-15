package com.pashynski.forum.feature.category;

import com.pashynski.forum.feature.category.dto.CategoryDto;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Transactional
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(Sort.by("priority").descending());
        return categoryEntities.stream().map(categoryMapper::toDto).toList();
    }

    @Retryable(
            value = OptimisticLockException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 20)
    )
    public void incrementTopicsCounter(UUID categoryId) {
        CategoryEntity category = categoryRepository.getReferenceById(categoryId);
        category.incrementTopicsCounter();
    }

    @Retryable(
            value = OptimisticLockException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 20)
    )
    public void incrementPostsCounter(UUID topicId) {
        CategoryEntity category = categoryRepository.findByTopicId(topicId);
        category.incrementPostsCounter();
    }
}
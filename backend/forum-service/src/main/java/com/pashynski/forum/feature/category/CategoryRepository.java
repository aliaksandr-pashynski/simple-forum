package com.pashynski.forum.feature.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    @Query("select c from CategoryEntity c join c.topicsEntities t where t.id = :topicId")
    CategoryEntity findByTopicId(UUID topicId);
}
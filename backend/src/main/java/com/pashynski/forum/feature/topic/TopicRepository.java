package com.pashynski.forum.feature.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {

    Page<TopicEntity> findAllByCategoryId(UUID categoryId, Pageable pageable);
}
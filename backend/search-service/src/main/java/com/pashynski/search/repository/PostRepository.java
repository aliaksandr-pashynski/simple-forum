package com.pashynski.search.repository;

import com.pashynski.search.documents.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

    Page<Post> findByTopicIdAndBodyMatches(UUID topicId, String searchText, Pageable pageable);
}
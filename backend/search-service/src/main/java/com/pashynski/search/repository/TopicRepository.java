package com.pashynski.search.repository;

import com.pashynski.search.documents.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRepository extends CrudRepository<Topic, UUID> {

    Page<Topic> findByNameMatches(String searchText, Pageable pageable);
}
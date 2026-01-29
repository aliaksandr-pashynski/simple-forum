package com.pashynski.search.service;

import com.pashynski.search.documents.Post;
import com.pashynski.search.dto.SearchPostRequest;
import com.pashynski.search.mapper.PostMapper;
import com.pashynski.search.repository.PostRepository;
import com.pashynski.shared.kafka.PostCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public void indexPost(PostCreated kafkaMessage) {
        Post document = postMapper.toElasticDocument(kafkaMessage);
        postRepository.save(document);
    }

    public Page<Post> searchPosts(SearchPostRequest request) {
        return postRepository.findByTopicIdAndBodyMatches(request.topicId(), request.query(), Pageable.ofSize(10));
    }
}
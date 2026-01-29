package com.pashynski.search.controller;

import com.pashynski.search.documents.Post;
import com.pashynski.search.dto.SearchPostRequest;
import com.pashynski.search.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchPostController {

    private final PostService postService;

    @PostMapping("/posts/search")
    public ResponseEntity<?> searchPosts(@RequestBody SearchPostRequest request) {
        Page<Post> posts = postService.searchPosts(request);
        return ResponseEntity.ok(posts);
    }
}
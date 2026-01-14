package com.pashynski.forum.feature.post;

import com.pashynski.forum.feature.forum.ForumService;
import com.pashynski.forum.feature.post.dto.PostDto;
import com.pashynski.forum.feature.post.dto.SavePostDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

    private final ForumService forumService;
    private final PostService postService;
    private final int postsMaxPageSize;

    public PostController(
            ForumService forumService,
            PostService postService,
            @Value("${posts.max-page-size}") int postsMaxPageSize
    ) {
        this.forumService = forumService;
        this.postService = postService;
        this.postsMaxPageSize = postsMaxPageSize;
    }

    @PostMapping("/posts")
    public ResponseEntity<?> savePost(
            @Valid @RequestBody SavePostDto savePostDto,
            @AuthenticationPrincipal Jwt token
    ) {
        PostDto postDto = forumService.savePost(savePostDto, UUID.fromString(token.getSubject()));
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(
            @RequestParam UUID topicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, Math.min(size, postsMaxPageSize));
        Page<PostDto> postsPage = postService.getAllPostsByTopic(topicId, pageable);
        return ResponseEntity.ok(postsPage);
    }
}
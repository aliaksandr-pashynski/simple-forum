package com.pashynski.forum.feature.post;

import com.pashynski.forum.feature.post.dto.PostDto;
import com.pashynski.forum.feature.post.dto.SavePostDto;
import com.pashynski.forum.feature.topic.TopicEntity;
import com.pashynski.forum.feature.user.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final EntityManager em;

    public PostDto savePost(SavePostDto savePostDto, UUID createdBy) {
        PostEntity postEntity = new PostEntity();
        postEntity.setBody(savePostDto.body());
        postEntity.setCreatedBy(em.getReference(UserEntity.class, createdBy));
        postEntity.setTopic(em.getReference(TopicEntity.class, savePostDto.topicId()));
        PostEntity savedPost = postRepository.saveAndFlush(postEntity);
        return postMapper.toDto(savedPost);
    }

    public Page<PostDto> getAllPostsByTopic(UUID topicId, Pageable pageable) {
        Page<PostEntity> allByTopicId = postRepository.findAllByTopicId(topicId, pageable);
        return allByTopicId.map(postMapper::toDto);
    }
}
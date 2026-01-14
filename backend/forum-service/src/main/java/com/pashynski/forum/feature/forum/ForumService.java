package com.pashynski.forum.feature.forum;

import com.pashynski.forum.feature.category.CategoryService;
import com.pashynski.forum.feature.file.FileService;
import com.pashynski.forum.feature.file.FileUploadResponse;
import com.pashynski.forum.feature.kafka.KafkaService;
import com.pashynski.forum.feature.post.PostService;
import com.pashynski.forum.feature.post.dto.PostDto;
import com.pashynski.forum.feature.post.dto.SavePostDto;
import com.pashynski.forum.feature.topic.TopicService;
import com.pashynski.forum.feature.topic.dto.SaveTopicDto;
import com.pashynski.forum.feature.topic.dto.TopicDto;
import com.pashynski.forum.feature.user.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class ForumService {

    private final CategoryService categoryService;
    private final TopicService topicService;
    private final PostService postService;
    private final FileService fileService;
    private final UserService userService;
    private final KafkaService kafkaService;

    public TopicDto saveTopic(SaveTopicDto saveTopicDto, UUID createdBy) {
        TopicDto topicDto = topicService.saveTopic(saveTopicDto, createdBy);
        categoryService.incrementTopicsCounter(saveTopicDto.categoryId());
        userService.incrementTopicsCounter(createdBy);
        kafkaService.topicCreated(saveTopicDto, createdBy);
        return topicDto;
    }

    public PostDto savePost(SavePostDto savePostDto, UUID createdBy) {
        PostDto postDto = postService.savePost(savePostDto, createdBy);
        topicService.incrementPostsCounter(savePostDto.topicId());
        categoryService.incrementPostsCounter(savePostDto.topicId());
        userService.incrementPostsCounter(createdBy);
        return postDto;
    }

    public FileUploadResponse uploadAvatar(UUID avatarOwner, MultipartFile file) {
        try {
            FileUploadResponse uploadResponse = fileService.uploadAvatar(file.getInputStream(), file.getContentType(), file.getSize());
            userService.updateAvatar(avatarOwner, uploadResponse.getFileInfo().objectName());
            return uploadResponse;
        } catch (IOException e) {
            return FileUploadResponse.error(e.getMessage());
        }
    }
}
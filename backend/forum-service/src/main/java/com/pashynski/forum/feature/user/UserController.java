package com.pashynski.forum.feature.user;

import com.pashynski.forum.feature.file.FileUploadResponse;
import com.pashynski.forum.feature.file.validators.FileSize;
import com.pashynski.forum.feature.file.validators.ImageFile;
import com.pashynski.forum.feature.forum.ForumService;
import com.pashynski.forum.feature.user.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private static final long MAX_BYTES = 512L * 1024L; // 512 Kb
    private final UserService userService;
    private final ForumService forumService;

    @GetMapping("/users/me")
    public ResponseEntity<?> getInfoAboutYourself(@AuthenticationPrincipal Jwt token) {
        UserDto user = userService.findById(UUID.fromString(token.getSubject())).orElse(null);
        if (user == null) {
            user = userService.saveUserFromJwtToken(token);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "users/avatar")
    public ResponseEntity<?> uploadAvatar(
            @RequestPart
            @NotNull
            @FileSize(max = MAX_BYTES)
            @ImageFile
            MultipartFile file,
            @AuthenticationPrincipal Jwt token
    ) {
        FileUploadResponse fileUploadResponse = forumService.uploadAvatar(UUID.fromString(token.getSubject()), file);
        return fileUploadResponse.isSuccess() ?
                ResponseEntity.ok(fileUploadResponse.getFileInfo()) :
                ResponseEntity.badRequest().body(fileUploadResponse.getError());
    }
}
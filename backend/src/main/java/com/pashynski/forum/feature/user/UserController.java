package com.pashynski.forum.feature.user;

import com.pashynski.forum.feature.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<?> getInfoAboutYourself(@AuthenticationPrincipal Jwt token) {
        UserDto user = userService.findById(UUID.fromString(token.getSubject())).orElse(null);
        if (user == null) {
            user = userService.saveUserFromJwtToken(token);
        }
        return ResponseEntity.ok(user);
    }
}
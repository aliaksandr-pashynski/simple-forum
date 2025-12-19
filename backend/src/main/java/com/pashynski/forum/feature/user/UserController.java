package com.pashynski.forum.feature.user;

import com.pashynski.forum.feature.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getById(@RequestParam UUID id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}
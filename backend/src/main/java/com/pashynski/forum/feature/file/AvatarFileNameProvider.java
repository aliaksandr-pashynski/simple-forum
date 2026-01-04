package com.pashynski.forum.feature.file;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AvatarFileNameProvider implements MinioObjectNameProvider {

    @Override
    public String getName() {
        return "avatar/" + UUID.randomUUID();
    }
}
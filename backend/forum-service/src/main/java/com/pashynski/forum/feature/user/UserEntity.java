package com.pashynski.forum.feature.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private UUID id;

    @Version
    private long version;

    private String username;

    private String email;

    @Column(name = "posts_created")
    private long postsCreated;

    @Column(name = "topics_created")
    private long topicsCreated;

    @CreationTimestamp
    @Column(name = "registered_at", updatable = false, insertable = false)
    private Instant registeredAt;

    private String avatar;

    public void incrementTopicsCounter() {
        this.topicsCreated++;
    }

    public void incrementPostsCounter() {
        this.postsCreated++;
    }
}
package com.pashynski.forum.feature.category;

import com.pashynski.forum.feature.topic.TopicEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Version
    private long version;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private long topics;

    private long posts;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "category")
    private List<TopicEntity> topicsEntities;

    public void incrementTopicsCounter() {
        this.topics++;
    }

    public void incrementPostsCounter() {
        this.posts++;
    }
}
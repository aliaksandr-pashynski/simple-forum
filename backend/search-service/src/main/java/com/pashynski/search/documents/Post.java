package com.pashynski.search.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Document(indexName = "posts")
public record Post(
        @Id
        UUID id,
        @Field(type = FieldType.Text)
        String body,
        @Field(type = FieldType.Keyword)
        UUID topicId
) {
}
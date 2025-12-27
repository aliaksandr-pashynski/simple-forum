package com.pashynski.forum.common;

public class DuplicateTopicNameException extends RuntimeException {
    public DuplicateTopicNameException(String message) {
        super(message);
    }
}
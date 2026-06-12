package com.example.blogapi.dto.comment;

import java.time.LocalDateTime;

public class CommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String authorUsername;
    private Long authorId;

    public CommentResponse(Long id, String content, LocalDateTime createdAt,
                           String authorUsername, Long authorId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.authorUsername = authorUsername;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public Long getAuthorId() {
        return authorId;
    }
}

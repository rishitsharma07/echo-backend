package com.example.blogapi.dto.comment;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {

    @NotBlank(message = "Content is required")
    private String content;

    public CommentRequest() {
    }

    public CommentRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

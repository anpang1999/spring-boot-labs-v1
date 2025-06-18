package com.example.ch4codeyourself.v3.dto;

import com.example.ch4codeyourself.v3.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String body;
    private String author;
    private LocalDateTime createdAt;

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor(),
                post.getCreatedAt()
        );
    }
}

package com.example.ch4codeyourself.v3.dto;

import com.example.ch4codeyourself.v3.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String title;
    private String body;
    private String author;

    public Post toDomain() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setBody(this.body);
        post.setAuthor(this.author);
        return post;
    }
}

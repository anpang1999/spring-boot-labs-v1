package com.example.ch4codeyourself.v4.dto.post;

import com.example.ch4codeyourself.v4.domain.Post;
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

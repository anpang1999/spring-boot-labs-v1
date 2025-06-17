package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateRequest {
    private String title;
    private String body;

    public Post toDomain(){
        Post post = new Post();
        post.setTitle(this.title);
        post.setBody(this.body);
        return post;
    } // toDomain 으로 타입 변환
}

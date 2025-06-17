package com.example.ch3codeyourself.dto;

import com.example.ch3codeyourself.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //매개변수가 없는 기본 생성자를 자동 생성

@AllArgsConstructor //모든 필드를 인자로 받는 생성자를 자동 생성

public class PostResponse {
    private Long id;
    private String title;
    private String body;

    // Post -> PostResponse
    public static PostResponse from(Post post){

        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }

}

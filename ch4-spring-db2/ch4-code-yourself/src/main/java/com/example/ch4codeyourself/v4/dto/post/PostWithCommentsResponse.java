package com.example.ch4codeyourself.v4.dto.post;

import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostWithCommentsResponse {

    private com.example.ch4codeyourself.v4.dto.post.PostResponse post;
    private Page<com.example.ch4codeyourself.v4.dto.comment.CommentResponse> comments;

    public static com.example.ch4codeyourself.v4.dto.post.PostWithCommentsResponse from(Post post, Page<CommentResponse> comments) {
        return com.example.ch4codeyourself.v4.dto.post.PostWithCommentsResponse.builder()
                .post(PostResponse.from(post))
                .comments(
                        comments
                )
                .build();
    }
}
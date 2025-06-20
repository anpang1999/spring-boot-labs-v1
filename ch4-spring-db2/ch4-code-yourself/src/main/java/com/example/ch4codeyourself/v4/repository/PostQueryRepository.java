package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {
    Page<Post> searchBYCreatedAtWithQueryDsl(LocalDateTime createdAt, Pageable pageable);

    Page<PostResponse> search(PostSearchRequest request);


}

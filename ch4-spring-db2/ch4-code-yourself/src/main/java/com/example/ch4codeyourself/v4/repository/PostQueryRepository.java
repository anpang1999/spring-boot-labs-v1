package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {
    // 작성일자 이후에 대한 조회
    Page<Post> searchByCreatedAtWithQueryDSL(LocalDateTime createdAt, Pageable pageable);
    Page<PostResponse> search(PostSearchRequest request);
}

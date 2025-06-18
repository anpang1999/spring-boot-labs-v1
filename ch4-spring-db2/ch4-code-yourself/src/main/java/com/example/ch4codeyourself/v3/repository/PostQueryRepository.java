package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PostQueryRepository {
    Page<Post> searchBYCreatedAtWithQueryDsl(LocalDateTime createdAt, Pageable pageable);
}

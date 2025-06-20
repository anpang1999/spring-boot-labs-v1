package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.QPost;
import com.example.ch4codeyourself.v3.dto.PostResponse;
import com.example.ch4codeyourself.v3.dto.PostSearchRequest;
import com.example.ch4codeyourself.v3.domain.Post;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public interface PostQueryRepository {
    Page<Post> searchBYCreatedAtWithQueryDsl(LocalDateTime createdAt, Pageable pageable);

    Page<PostResponse> search(PostSearchRequest request);


}

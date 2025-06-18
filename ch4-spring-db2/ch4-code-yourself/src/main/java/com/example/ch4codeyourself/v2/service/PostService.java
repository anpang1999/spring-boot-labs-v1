package com.example.ch4codeyourself.v2.service;


import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.*;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    // mapper -> JPA repository 로 마이그레이션
    private final PostRepository postRepository ;



    public PostResponse createPost(PostCreateRequest request) {
        Post post = request.toDomain();
        Post saved = postRepository.save(post);
        return PostResponse.from(saved);
    }

    // 키워드가 제목에 포함된 게시글 가져오기
    @Transactional(readOnly = true)
    public PostPageResponse getAllPosts(PostSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<PostResponse> page = postRepository.findByTitleContaining(request.getKeyword(), pageable)
                .map(PostResponse::from);

        return PostPageResponse.from(page.getContent(), request, page.getTotalElements());
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return postRepository.findById(id)
                .map(PostResponse::from)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

        // 트랜잭션이 끝날 때.
        // 변경이 일어나면 dirty checking -> SQL


    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));

        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
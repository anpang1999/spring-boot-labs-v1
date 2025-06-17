package com.example.ch3codeyourself.service;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.*;
import com.example.ch3codeyourself.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor // bean 생성자 주입
public class PostService {

    // MyBatis
    private final PostMapper postMapper;

    public PostResponse createPost(PostCreateRequest request) {
        // PostCreateRequest -> Post 변환
        Post post = request.toDomain();
        postMapper.save(post);

        // Post -> PostResponse 반환
        return PostResponse.from(post);
    }

//    public List<PostResponse> getPosts() {
//        return postMapper.findAll().stream()
//                .map(post->PostResponse.from(post))
//                .toList();
//
//    }

    public PostResponse getPost(Long id) {
        Post post = postMapper.findById(id);
        return PostResponse.from(post);
    }

    public void updatePost(Long id, PostUpdateRequest request) {
        Post post = postMapper.findById(id);
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        postMapper.update(post);
    }

    public void deletePost(Long id) {
        postMapper.delete(id);
    }

    public PostPageResponse searchPosts(PostSearchRequest search) {
        // Post -> PostResponse
        List<PostResponse> posts = postMapper.findAllWithSearch(search).stream()
                .map(post->PostResponse.from(post))
                .toList();
        int count = postMapper.count(search);

        return PostPageResponse.from(posts, search, count);
    }
}

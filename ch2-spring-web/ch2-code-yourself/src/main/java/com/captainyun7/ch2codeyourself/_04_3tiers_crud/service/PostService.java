package com.captainyun7.ch2codeyourself._04_3tiers_crud.service;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostCreateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostUpdateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.exception.PostNotFoundException;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;

    public PostResponse getPost(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        return toResponse(post);
    }

    public List<PostResponse> getAllPosts(){
         return repository.findAll().stream()
                 .map(post -> toResponse(post))
                 .collect(Collectors.toList());
    }

    // Post 객체 -> PostResponse 객체로 변환
    public PostResponse toResponse(Post post){
        return new PostResponse(post.getId(), post.getTitle(), post.getBody());
    }

    public  PostResponse createPost(PostCreateRequest request){
        // dto -> domain 변환
        Post post = new Post(null,request.getTitle(),request.getBody());
        Post savedPost =  repository.save(post);
        return toResponse(savedPost);
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }

    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        // (1) id를 통해 수정할 게시글을 가져온다.
        // (2) 가져왔다면 (null이 아니면), request의 값으로 수정한다.
        Post post = repository.findById(id)
                .orElseThrow(()->new RuntimeException(("Post not found")));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        repository.save(post);
        return  toResponse(post);
    }
}

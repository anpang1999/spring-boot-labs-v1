package com.captainyun7.ch2codeyourself._04_3tiers_crud.controller;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostCreateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostResponse;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.dto.PostUpdateRequest;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // (1) 클라이언트 : "게시글 하나 줘. id는 이거야"
    // (2) 컨트롤러 -> 서비스
    // (3) 서비스 -> 레포지토리에게 위임
    // (4) 레포지토리 -> 서비스에게 게시글 데이터 전달
    // (5) 서비스 -> 컨트롤러에게 모든 게시글 반환 (dto)

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id)); //게시글 하나
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts()); //게시글 다중 조회
    }

    // POST 요청 : 클라이언트 -> 서버
    // HTTP Body
    // {
    //  "title": -> dto 의 title 필드 값으로 매핑
    //  "body" : -> dto의 body 필드 값으로 매핑
    //  }
    // Post/ posts
    // 리턴 : 등록한 객체를 반환 (dto)
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postService.createPost(request));
    }

    // PUT /api/v1/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request){
        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    // DELETE /api/v1/posts/{id}
    // 개선사항 : 예외 처리
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return  ResponseEntity.noContent().build();
    }

    }




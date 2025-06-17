package com.example.ch3codeyourself.controller;

import com.example.ch3codeyourself.dto.*;
import com.example.ch3codeyourself.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private  final PostService postService;


    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost((request))); // 요청을 받아서 서비스 호출
    }

    // /posts
    // /posts?keyword=스프링
//    @GetMapping
//    public ResponseEntity<List<PostResponse>> getPosts(){
//        return ResponseEntity.ok(postService.getPosts());
//    }

    @GetMapping()
    public ResponseEntity<PostPageResponse> list(PostSearchRequest search){
        return ResponseEntity.ok(postService.searchPosts(search));
    }

   @GetMapping("/{id}")
   public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
   }

   @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request){
        postService.updatePost(id,request);
        return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
   }
}

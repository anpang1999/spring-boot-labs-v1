package com.captainyun7.ch2codeyourself._03_rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/v2")

public class RequestController02 {
    // ResponseEntity : 스프링의 Http 응답 객체
    // HTTP : 헤더 + 바디 + 상태코드

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        Post post = new Post("샘플 게시글", "샘플 내용입니다...");
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫번째 게시글", "내용 1"));
        posts.add(new Post("두번째 게시글", "내용 2"));
        posts.add(new Post("세번째 게시글", "내용 3"));
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> post(@RequestBody Post post) {
        post.setId(1L);
        return ResponseEntity.ok(post);
    }

    // HTTP 상태 코드
    // 200 : 성공
    // 300 : 리다이레트, 진행중
    // 400 : 에러 (너 실수)
    // 500 : 에러 (내 실수)

    @PostMapping("/201")
    public ResponseEntity<String> get201() {
        return ResponseEntity.status(HttpStatus.CREATED).body("201");
    }

    @DeleteMapping("/204")
    public ResponseEntity<String> delete204() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204");
    }

    @GetMapping("/401")
    public ResponseEntity<String> get401() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
    }

    @GetMapping("/500")
    public ResponseEntity<String> get500() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
    }
}

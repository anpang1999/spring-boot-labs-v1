package com.example.ch4codeyourself.v4.controller;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.dto.comment.CommentCreateRequest;
import com.example.ch4codeyourself.v4.dto.comment.CommentPageResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentUpdateRequest;
import com.example.ch4codeyourself.v4.repository.CommentRepository;
import com.example.ch4codeyourself.v4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId,
                                                         @RequestBody CommentCreateRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(postId,request));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPageResponse> getComments(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size)
    {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId,page,size));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request) {
        CommentResponse response = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

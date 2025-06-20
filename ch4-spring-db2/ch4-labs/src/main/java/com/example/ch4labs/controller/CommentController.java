package com.example.ch4labs.controller;

import com.example.ch4labs.dto.comment.CommentCreateRequest;
import com.example.ch4labs.dto.comment.CommentResponse;
import com.example.ch4labs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/reviews/{reviewsId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long reviewId,
                                                         @RequestBody CommentCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(reviewId,request));
    }

    @GetMapping("/reviews/{reviewsId}/reviews")
    public ResponseEntity<CommentResponse> getComments(@PathVariable Long reviewId){

        return ResponseEntity.ok(commentService.getCommentByReview(reviewId));
    }


}

package com.example.ch4labs.service;

import com.example.ch4labs.domain.Comment;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.comment.CommentCreateRequest;
import com.example.ch4labs.dto.comment.CommentResponse;
import com.example.ch4labs.dto.review.ReviewResponse;
import com.example.ch4labs.repository.CommentRepository;
import com.example.ch4labs.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;


    public CommentResponse createComment(Long reviewId, CommentCreateRequest request) {

        Review review  = reviewRepository.findById(reviewId)
                .orElseThrow(()->new EntityNotFoundException(("Review not found")));

        Comment comment  = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .review(review)
                .build();

        Comment saved =  commentRepository.save(comment);

        return  CommentResponse.from(saved);
    }

//    public CommentResponse getCommentByReview(Long reviewId) {
//        Review review  =  reviewRepository.findByReviewId(reviewId);
//        return ReviewResponse.from(comments.map(CommentResponse::from));
//    }
}

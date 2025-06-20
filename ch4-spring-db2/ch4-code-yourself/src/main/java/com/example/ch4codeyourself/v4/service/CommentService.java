package com.example.ch4codeyourself.v4.service;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.comment.CommentCreateRequest;
import com.example.ch4codeyourself.v4.dto.comment.CommentPageResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentResponse;
import com.example.ch4codeyourself.v4.dto.comment.CommentUpdateRequest;
import com.example.ch4codeyourself.v4.repository.CommentRepository;
import com.example.ch4codeyourself.v4.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponse createComment(Long postId, CommentCreateRequest request) {

        Post post = postRepository.findById(postId)
                        .orElseThrow(()->new EntityNotFoundException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();

        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }

    public CommentPageResponse getCommentsByPost(Long postId, int page, int size) {
        Page<Comment> comments = commentRepository.findByPostId(postId, PageRequest.of(page,size));
        return CommentPageResponse.from(comments.map(CommentResponse::from));
    }

    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다." + commentId));

        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }

}

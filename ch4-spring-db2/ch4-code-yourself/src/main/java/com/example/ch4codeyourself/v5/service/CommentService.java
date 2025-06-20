package com.example.ch4codeyourself.v5.service;

import com.example.ch4codeyourself.v5.domain.Comment;
import com.example.ch4codeyourself.v5.domain.Post;
import com.example.ch4codeyourself.v5.dto.comment.*;
import com.example.ch4codeyourself.v5.repository.CommentRepository;
import com.example.ch4codeyourself.v5.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();

        if (request.getParentId() != null) {
            // 부모 댓글 조회
            Comment parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
            // 자식 댓글
            comment.setParent(parent);
        }

        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);
    }


    public CommentPageResponse getCommentsByPost(Long postId, CommentSearchRequest request) {

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(),
                Sort.by("createdAt").descending());

        // 2가지 분기
        if (request.isHierarchical() == true) {
            // 계층 구조로 보여주세요.

        } else {
            // 플랫(평면) 구조로 보여주세요.
        }
        return  null;
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

package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.Comment;
import com.example.ch4codeyourself.v4.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);


}

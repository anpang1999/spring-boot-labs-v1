package com.example.ch4labs.repository;

import com.example.ch4labs.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByTitleContaining(String keyword, Pageable pageable);

    Page<Review> findByAuthor(String author, Pageable pageable);

    Page<Review> findByTitleContainingAndAuthor(String keyword, String author, Pageable pageable);

    Page<Review> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.createdAt >= :createdAt")
    Page<Review> searchByCreatedAt(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.title LIKE %:keyword% AND r.author LIKE %:author%")
    Page<Review> searchByTitleContainingAndAuthor(@Param("keyword") String keyword,
                                                  @Param("author") String author,
                                                  Pageable pageable);
}

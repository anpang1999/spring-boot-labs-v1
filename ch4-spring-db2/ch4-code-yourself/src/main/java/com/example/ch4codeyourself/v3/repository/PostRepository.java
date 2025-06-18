package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>, PostQueryRepository {
    // 자동 생성 메서드 (= JPA가 메서드명만 보고 자동으로 만들어줌
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    List<Post> findByTitleContaining(String keyword);

    // 작성자 일치
    Page<Post> findByAuthor(String author, Pageable pageable);
    // findBy[필드][조건]AND|OR[필드][조건]정렬
    // CRUD 자동 생성 (JPA)

    // 제목에 검색어 포함 + 작성자 일치
    Page<Post> findByTitleContainingAndAuthor(String keyword, String author, Pageable pageable);

    // 작성일자 이후에 대한 조회
    Page<Post> findByCreatedAtAfter(LocalDateTime createAt, Pageable pageable);

    // JPQL

    // SELECT * FROM posts WHERE created_at <= '2025-06-10T00:00'
    @Query("SELECT p FROM Post p WHERE p.createdAt  >= :createdAt")
    Page<Post> searchByAuthorAndTitle(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% AND p.author LIKE %:author%")
    Page<Post> searchByTitleContainingAndAuthor(@Param("keyword")String keyword, @Param("author")String author,Pageable pageable);
}

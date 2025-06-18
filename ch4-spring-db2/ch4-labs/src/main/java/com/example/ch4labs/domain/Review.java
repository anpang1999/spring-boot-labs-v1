package com.example.ch4labs.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 리뷰 식별자

    private String title;  // 리뷰 제목

    @Column(columnDefinition = "TEXT")
    private String content;  // 리뷰 본문

    private String author;  // 리뷰 작성자

    private String bookTitle;  // 리뷰 대상 책 제목

    private String bookAuthor;  // 책 저자

    @Column(nullable = false)
    private int rating;  // 평점 (1~5 정수)

    @CreationTimestamp
    private LocalDateTime createdAt;  // 생성 시간
}

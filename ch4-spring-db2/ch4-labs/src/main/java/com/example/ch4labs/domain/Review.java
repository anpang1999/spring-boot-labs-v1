package com.example.ch4labs.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title; // 리뷰 제목

    private String content; // 리뷰 본문

    private String author; // 리뷰 작성자

    private String bookTitle; // 리뷰 대상 책 제목

    private String bookAuthor; // 책 저자

    private Integer rating; // 평점 (1~5 점수)
}

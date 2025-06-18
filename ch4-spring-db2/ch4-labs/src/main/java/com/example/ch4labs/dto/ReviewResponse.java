package com.example.ch4labs.dto;

import com.example.ch4labs.domain.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .author(review.getAuthor())
                .bookTitle(review.getBookTitle())
                .bookAuthor(review.getBookAuthor())
                .rating(review.getRating())
                .build();
    }
}

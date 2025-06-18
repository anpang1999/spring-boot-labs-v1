package com.example.ch4labs.dto;

import com.example.ch4labs.domain.Review;
import lombok.Getter;

@Getter
public class ReviewCreateRequest {
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private int rating;

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .author(author)
                .bookTitle(bookTitle)
                .bookAuthor(bookAuthor)
                .rating(rating)
                .build();
    }
}

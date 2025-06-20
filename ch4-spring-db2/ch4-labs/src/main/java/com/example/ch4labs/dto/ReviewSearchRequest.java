package com.example.ch4labs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchRequest {
    private String author;
    private String bookTitle;
    private String keyword;
    private Integer rating;
    private Integer minRating;
    private Integer maxRating;
    private int page = 0;
    private int size = 10;

    private String bookTitleContains;
    private String bookAuthor;
    private String titleContains;
    private String contentContains;
    private String sort; // e.g., "createdAt,desc" or "rating,asc"

}

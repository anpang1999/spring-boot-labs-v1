package com.example.ch4labs.dto;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    private String title;
    private String author;
    private String content;
    private String bookTitle;
    private String bookAuthor;
    private int rating;
}

package com.example.ch4labs.dto;

import com.example.ch4labs.domain.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String bookTitle;
    private String bookAuthor;
    private Integer rating;

    public static ReviewResponse from(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setTitle(review.getTitle());
        response.setContent(review.getContent());
        response.setAuthor(review.getAuthor());
        response.setBookTitle(review.getBookTitle());
        response.setBookAuthor(review.getBookAuthor());
        response.setRating(review.getRating());
        return response;
    }
}

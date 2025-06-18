package com.example.ch4labs.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewPageResponse {
    private List<ReviewResponse> reviews;
    private int page;
    private int size;
    private long totalCount;

    public static ReviewPageResponse from(List<ReviewResponse> reviews, ReviewSearchRequest request, long count) {
        return ReviewPageResponse.builder()
                .reviews(reviews)
                .page(request.getPage())
                .size(request.getSize())
                .totalCount(count)
                .build();
    }
}

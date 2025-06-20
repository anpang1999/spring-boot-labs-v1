package com.example.ch4labs.dto.review;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class ReviewPageResponse {
    private List<ReviewResponse> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int page;

    public static ReviewPageResponse from(Page<ReviewResponse> pageData) {
        return ReviewPageResponse.builder()
                .content(pageData.getContent())
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .size(pageData.getSize())
                .page(pageData.getNumber())
                .build();
    }
}
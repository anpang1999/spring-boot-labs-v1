package com.example.ch4labs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchRequest {
    private String keyword;
    private int page = 1;
    private int size = 10;

    public int getOffset() {
        return (page - 1) * size;
    }
}

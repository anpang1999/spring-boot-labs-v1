package com.example.ch3codeyourself.dto;

import lombok.Data;

@Data
public class PostSearchRequest {
    private String keyword = ""; // 기본값
    private int page =10;
    private int size=0;

    private int getOffset(){
        return (page -1 ) * size;
    }
}

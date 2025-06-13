package com.example.ch2labs.labs07.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequest {
    private String title;
    private boolean completed;
}

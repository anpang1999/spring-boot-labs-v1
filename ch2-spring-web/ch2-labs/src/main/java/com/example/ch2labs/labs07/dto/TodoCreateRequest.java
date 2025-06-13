package com.example.ch2labs.labs07.dto;

import lombok.Getter;

@Getter
public class TodoCreateRequest {
    private String title;
    private boolean completed;
}

package com.example.ch2labs.labs07.controller;

import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.dto.TodoUpdateRequest;
import com.example.ch2labs.labs07.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createPost(@RequestBody TodoCreateRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createPost(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updatePost(@PathVariable Long id, @RequestBody TodoUpdateRequest request){
        return ResponseEntity.ok(todoService.updatePost(id,request));
    }


}

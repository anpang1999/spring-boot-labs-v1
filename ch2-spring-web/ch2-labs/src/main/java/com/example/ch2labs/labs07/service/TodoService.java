package com.example.ch2labs.labs07.service;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.dto.TodoUpdateRequest;
import com.example.ch2labs.labs07.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //클래스의 final 필드나 @NonNull이 붙은 필드만 매개변수로 받는 생성자를 자동 생성해줍니다.
public class TodoService {

    private final TodoRepository repository;

    public List<TodoResponse> getAllTodos() {
        return repository.findAll().stream()
                .map(todo->toResponse((Todo) todo))
                .collect(Collectors.toList());
    }

    private TodoResponse toResponse(Todo todo) {
        return new TodoResponse(todo.getId(),todo.getTitle(),todo.isCompleted());
    }

    public TodoResponse createPost(TodoCreateRequest request) {
        Todo todo = new Todo(null,request.getTitle(),request.isCompleted());
        Todo savedTodo  = repository.save(todo);
        return toResponse(savedTodo);
    }

    public TodoResponse updatePost(Long id, TodoUpdateRequest request) {
        Todo todo  = repository.findById(id)
                        .orElseThrow(()->new RuntimeException(("찾을 수 없는 id")));
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        repository.save(todo);
        return toResponse(todo);
    }
}

package com.example.ch2labs.labs07.config;

import com.example.ch2labs.labs07.domain.Todo;
import com.example.ch2labs.labs07.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Datainit {
    private final TodoRepository repository;

    @PostConstruct
    public void init() {
        repository.save(new Todo(null,"과제하기",false));        repository.save(new Todo(null,"과제하기",false));
        repository.save(new Todo(null,"과제하기2",false));
        repository.save(new Todo(null,"과제하기3",false));


    }
}

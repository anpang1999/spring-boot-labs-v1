package com.example.ch3codeyourself.mapper;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);

    List<Post> findAll();

    Post findById(Long id);

    void update(Post post);

    void delete(Long id);

    List<Post> findAllWithSearch(PostSearchRequest search);

    int count(PostSearchRequest search);
}

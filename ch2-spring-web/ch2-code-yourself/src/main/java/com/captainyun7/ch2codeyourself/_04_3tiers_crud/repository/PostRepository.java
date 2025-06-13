package com.captainyun7.ch2codeyourself._04_3tiers_crud.repository;


import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {


    // db
    // key 중복 X , value -> Post

    private final Map<Long, Post> store = new HashMap<>();
    private Long sequence = 0L;

    // 게시글 CRUD  = Create, Read, Update, Delete
    //
    public Post save(Post post) {
        post.setId(sequence++);
        store.put(post.getId(), post);
        return post;
    }

//    public Post get(Long id){
//        return store.get(id);
//    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(Long id) {
        store.remove(id);
    }

}

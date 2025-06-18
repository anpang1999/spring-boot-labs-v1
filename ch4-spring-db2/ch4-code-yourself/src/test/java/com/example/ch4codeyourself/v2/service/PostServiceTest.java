package com.example.ch4codeyourself.v2.service;

import com.example.ch4codeyourself.v2.domain.Post;
import com.example.ch4codeyourself.v2.dto.PostCreateRequest;
import com.example.ch4codeyourself.v2.dto.PostResponse;
import com.example.ch4codeyourself.v2.dto.PostSearchRequest;
import com.example.ch4codeyourself.v2.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    void 게시글_생성_조회_성공() {
        // given
        PostCreateRequest request = new PostCreateRequest("테스트 제목", "테스트 내용");

        // when
        PostResponse saved = postService.createPost(request);
        PostResponse found = postService.getPostById(saved.getId());

        // then
        assertThat(found.getTitle()).isEqualTo("테스트 제목");
        assertThat(found.getBody()).isEqualTo("테스트 내용");
    }

    @Test
    void 게시글_검색_페이징() {
        // given
        // 가데이터 20개 게시글 생성
        for (int i =1 ; i<=20; i++){
            postRepository.save(new Post(null,"게시글 "+i,"내용"));
        }

        PostSearchRequest request = new PostSearchRequest("게시글 1",0,10);

        // when
//        portService.getAllPosts(request);

        // then
    }
}
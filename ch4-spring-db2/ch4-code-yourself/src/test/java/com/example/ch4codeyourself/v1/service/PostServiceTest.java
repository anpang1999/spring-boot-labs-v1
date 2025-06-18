package com.example.ch4codeyourself.v1.service;

import com.example.ch4codeyourself.v1.domain.Post;
import com.example.ch4codeyourself.v1.dto.PostCreateRequest;
import com.example.ch4codeyourself.v1.dto.PostResponse;
import com.example.ch4codeyourself.v1.dto.PostUpdateRequest;
import com.example.ch4codeyourself.v1.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

// Junit : 테스트 코드 실행 주체
// Mockito : Mocking 기능

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    // 테스트를 위해 필요한 객체
    // (1) PostService
    // (2) PostRepository

    // 가짜 객체로 생성
    @Mock
    private PostRepository  postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 게시글_생성_성공() {
        // PostCreateRequest 객체 필요
        // PostRepository가 정상 실행된다는 걸 가정
        // postResponse 리턴값을 검증

        // Given (조건)
        PostCreateRequest request = new PostCreateRequest("테스트 게시글","내용");

        // postRepository.save(post) = 리턴값 지정
        given(postRepository.save(any(Post.class)))
                .willReturn(new Post(1L,"테스트 게시글","내용"));

        // When (테스트 대상)
        PostResponse result = postService.createPost(request);

        // Then (검증)
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("테스트 게시글");
        assertThat(result.getBody()).isEqualTo("내용");

    }

    @Test
    void 게시글_수정_성공(){
        // given
        Long id = 1L;
        PostUpdateRequest request = new PostUpdateRequest("수정 제목", "수정 내용");
        Post post = new Post(id,"기존 제목", "기존 내용");

        given(postRepository.findById(id)).willReturn(Optional.of(post));

        // when
        PostResponse result = postService.updatePost(id, request);

        // Then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getTitle()).isEqualTo("수정 제목");
        assertThat(result.getBody()).isEqualTo("수정 내용");


        verify(postRepository).findById(id);
    }


}
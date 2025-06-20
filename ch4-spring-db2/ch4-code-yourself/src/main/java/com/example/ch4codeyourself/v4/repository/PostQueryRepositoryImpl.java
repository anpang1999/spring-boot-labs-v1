package com.example.ch4codeyourself.v4.repository;

import com.example.ch4codeyourself.v4.domain.QPost;
import com.example.ch4codeyourself.v4.domain.Post;
import com.example.ch4codeyourself.v4.dto.post.PostResponse;
import com.example.ch4codeyourself.v4.dto.post.PostSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PostQueryRepositoryImpl implements PostQueryRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Post> searchBYCreatedAtWithQueryDsl(LocalDateTime createdAt, Pageable pageable) {

        QPost post = QPost.post;

        // page, size

        List<Post> content = queryFactory
                .selectFrom(post)
                .where(post.createdAt.goe(createdAt))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



        Long count = queryFactory
                .select(post.count())
                .from(post)
                .where(post.createdAt.goe(createdAt))
                .fetchOne();

        return new PageImpl<>(content, pageable, count != null ? count : 0);
    }

    @Override
    public Page<PostResponse> search(PostSearchRequest request) {
        // QueryDSL로 동적 쿼리 만들기
        QPost post = QPost.post;

        // BooleanBuilder
        BooleanBuilder builder = new BooleanBuilder();

        //        // SELECT * FROM posts;
        //        queryFactory.selectFrom(post)
        //                .fetch();

        // /api/v1/posts?keyword=스프링&author=yun&page=2&size=5
        // 검색어 포함 (제목이나 본문)

        if (StringUtils.hasText(request.getKeyword())){
            builder.and(post.title.containsIgnoreCase(request.getKeyword()))
                    .or (post.title.containsIgnoreCase(request.getKeyword()));
        }

        // 작성자 일치 여부 WHERE author = request.getAuthor()
        if (StringUtils.hasText(request.getAuthor())){
            builder.and(post.author.eq(request.getAuthor()));
        }

        // 작성일 이후
        if (request.getCreatedAt() != null){
            builder.and(post.createdAt.goe(request.getCreatedAt()));
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Post> posts = queryFactory.selectFrom(post)
                .where(builder)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();


        long total = queryFactory.select(post.count())
                .from(post)
                .where(builder)
                .fetchOne();

        List<PostResponse> content = posts.stream().map(PostResponse::from).toList();
        return new PageImpl<>(content,pageable,posts.size());
    }

}

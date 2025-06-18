package com.example.ch4codeyourself.v3.repository;

import com.example.ch4codeyourself.v3.domain.Post;
import com.example.ch4codeyourself.v3.domain.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
}

package com.example.ch4labs.repository;

import com.example.ch4labs.domain.QReview;
import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.ReviewSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> searchByConditions(ReviewSearchRequest request) {
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();

        if (request.getAuthor() != null && !request.getAuthor().isEmpty()) {
            builder.and(review.author.eq(request.getAuthor()));
        }

        if (request.getBookTitle() != null && !request.getBookTitle().isEmpty()) {
            builder.and(review.bookTitle.containsIgnoreCase(request.getBookTitle()));
        }

        if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
            builder.and(
                    review.title.containsIgnoreCase(request.getKeyword())
                            .or(review.content.containsIgnoreCase(request.getKeyword()))
            );
        }

        if (request.getRating() != null) {
            builder.and(review.rating.eq(request.getRating()));
        }

        if (request.getMinRating() != null) {
            builder.and(review.rating.goe(request.getMinRating()));
        }

        if (request.getMaxRating() != null) {
            builder.and(review.rating.loe(request.getMaxRating()));
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

        List<Review> results = queryFactory
                .selectFrom(review)
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(review)
                .where(builder)
                .fetchCount();

        return new PageImpl<>(results, pageable, total);

    }
}


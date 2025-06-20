package com.example.ch4labs.service;

import com.example.ch4labs.domain.Review;
import com.example.ch4labs.dto.*;
import com.example.ch4labs.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewCreateRequest request) {
        Review review = request.toEntity();
        Review saved = reviewRepository.save(review);
        return ReviewResponse.from(saved);
    }

    public Page<ReviewResponse> search(ReviewSearchRequest request) {
        return reviewRepository.searchByConditions(request)
                .map(ReviewResponse::from);
    }

    public ReviewResponse updateReview(Long id, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 존재하지 않습니다."));

        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setAuthor(request.getAuthor());
        review.setBookTitle(request.getBookTitle());
        review.setBookAuthor(request.getBookAuthor());
        review.setRating(request.getRating());

        return ReviewResponse.from(review);
    }


    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

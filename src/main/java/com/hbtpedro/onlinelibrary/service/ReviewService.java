package com.hbtpedro.onlinelibrary.service;

import com.hbtpedro.onlinelibrary.domain.DTO.ReviewDTO;
import com.hbtpedro.onlinelibrary.domain.Review;
import com.hbtpedro.onlinelibrary.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final BookService bookService;


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id " + id + " not found"));
    }

    @CacheEvict(cacheNames = "rating", allEntries = true)
    public Review saveReview(ReviewDTO reviewDTO) {

        Review review = Review.builder()
                .user(userService.getUserById(reviewDTO.getUserId()))
                .book(bookService.getBookById(reviewDTO.getBookId()))
                .comment(reviewDTO.getComment())
                .rating(reviewDTO.getRating())
                .build();

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
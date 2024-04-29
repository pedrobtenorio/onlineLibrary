package com.hbtpedro.onlinelibrary.repository;

import com.hbtpedro.onlinelibrary.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

package com.hbtpedro.onlinelibrary.domain.DTO;

import com.hbtpedro.onlinelibrary.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class BookAverageRatingDTO {
    private final Book book;
    private Double averageRating;

    public String getAverageRating() {
        if (averageRating == null) {
            return null;
        }
        return String.format("%.1f", averageRating);
    }
}

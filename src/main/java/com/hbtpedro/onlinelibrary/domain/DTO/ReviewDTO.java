package com.hbtpedro.onlinelibrary.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private Long userId;
    private Long bookId;
    private Integer rating;
    private String comment;

}

package com.hbtpedro.onlinelibrary.domain.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hbtpedro.onlinelibrary.domain.Book;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSearchResultDTO {

    private Book book;
    private Long availableCopies;
    private LocalDate expectedReturnDate;

}
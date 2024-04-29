package com.hbtpedro.onlinelibrary.service;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.DTO.BookAverageRatingDTO;
import com.hbtpedro.onlinelibrary.domain.DTO.BookSearchResultDTO;
import com.hbtpedro.onlinelibrary.domain.Review;
import com.hbtpedro.onlinelibrary.repository.BookRepository;
import com.hbtpedro.onlinelibrary.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@CacheConfig(cacheNames = "books")
@RequiredArgsConstructor
public class BookService {



    private final BookRepository bookRepository;


    @Cacheable(key = "'all'")
    public List<Book> getAllBooks() {
        simulateServerDelay();
        return bookRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Book getBookById(Long id) {
        simulateServerDelay();
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Book with id " + id + " not found"));
    }

    @CacheEvict(cacheNames = "books", key = "'all'")
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "books", key = "#id"),
            @CacheEvict(cacheNames = "books", key = "'all'")
    })
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "books", key = "#id"),
            @CacheEvict(cacheNames = "books", key = "'all'")
    })
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Book with id " + id + " not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());

        return bookRepository.save(existingBook);
    }

    @Cacheable(cacheNames = "rating")
    public List<BookAverageRatingDTO> getTopBooksByAverageRating() {
        simulateServerDelay();
        List<Object[]> topBooksByAverageRating = bookRepository.findTopBooksByAverageRating();

        return topBooksByAverageRating.stream()
                .map(result -> {
                    Book book = (Book) result[0];
                    Double averageRating = (Double) result[1];
                    return new BookAverageRatingDTO(book, averageRating);
                })
                .collect(Collectors.toList());
    }


    public List<BookSearchResultDTO> searchBooks(String title, String author, String genre) {
        List<Object[]> results = bookRepository.searchBooks(title, author, genre);
        List<BookSearchResultDTO> bookSearchResults = new ArrayList<>();
        for (Object[] result : results) {
            Book book = (Book) result[0];
            Long availableCopies = (Long) result[1];
            LocalDate expectedReturnDate = (LocalDate) result[2];

            BookSearchResultDTO dto = new BookSearchResultDTO();
            dto.setBook(book);
            dto.setAvailableCopies(availableCopies);
            if(availableCopies == 0) {
                dto.setExpectedReturnDate(expectedReturnDate);
            }
            bookSearchResults.add(dto);
        }

        return bookSearchResults;
    }

    private void simulateServerDelay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread sleep interrupted", e);
        }
    }
}

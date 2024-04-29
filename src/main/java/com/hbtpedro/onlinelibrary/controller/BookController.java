package com.hbtpedro.onlinelibrary.controller;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.DTO.BookAverageRatingDTO;
import com.hbtpedro.onlinelibrary.domain.DTO.BookSearchResultDTO;
import com.hbtpedro.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/best-books")
    public ResponseEntity<List<BookAverageRatingDTO>> getBestBooks() {
        List<BookAverageRatingDTO> books = bookService.getTopBooksByAverageRating();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/search")
    public List<BookSearchResultDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre
    ) {
        return bookService.searchBooks(title, author, genre);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

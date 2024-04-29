package com.hbtpedro.onlinelibrary.controller;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.BookCopy;
import com.hbtpedro.onlinelibrary.service.BookCopyService;
import com.hbtpedro.onlinelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copies")
@RequiredArgsConstructor
public class BookCopyController {
    private final BookCopyService bookCopyService;

    @GetMapping
    public ResponseEntity<List<BookCopy>> getAllCopies() {
        List<BookCopy> copies = bookCopyService.getAllBookCopies();
        return ResponseEntity.ok(copies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookCopy> getCopyById(@PathVariable Long id) {
        BookCopy copy = bookCopyService.getBookCopy(id);
        return ResponseEntity.ok(copy);
    }

    @PostMapping
    public ResponseEntity<BookCopy> saveCopy(@RequestBody BookCopy copy) {
        BookCopy saveBookCopy = bookCopyService.saveBookCopy(copy);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBookCopy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long id) {
        bookCopyService.deleteBookCopy(id);
        return ResponseEntity.noContent().build();
    }
}

package com.hbtpedro.onlinelibrary.service;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.BookCopy;
import com.hbtpedro.onlinelibrary.domain.enums.CopyStatus;
import com.hbtpedro.onlinelibrary.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;

    @Autowired
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public List<BookCopy> getAllBookCopiesByBook(Book book) {
        return bookCopyRepository.findAllByBook(book);
    }

    public BookCopy getBookCopy(Long id) {
        return bookCopyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy with id " + id + "not found"));
    }

    public void makeAvailable(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    public void makeBorrowed(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.BORROWED);
        bookCopyRepository.save(bookCopy);
    }


    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(Long id) {
        bookCopyRepository.deleteById(id);
    }
}
package com.hbtpedro.onlinelibrary.repository;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    List<BookCopy> findAllByBook(Book book);
}

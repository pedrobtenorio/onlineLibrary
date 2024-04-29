package com.hbtpedro.onlinelibrary.repository;

import com.hbtpedro.onlinelibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT r.book, AVG(r.rating) AS averageRating " +
            "FROM Review r " +
            "GROUP BY r.book " +
            "ORDER BY averageRating DESC")
    List<Object[]> findTopBooksByAverageRating();

    @Query("SELECT b, " +
            "       COUNT(c) AS availableCopies, " +
            "       MIN(CASE WHEN c.status = com.hbtpedro.onlinelibrary.domain.enums.CopyStatus.AVAILABLE THEN NULL " +
            "                ELSE r.dueDate END) AS expectedReturnDate " +
            "FROM Book b " +
            "LEFT JOIN b.copies c " +
            "LEFT JOIN c.reservations r " +
            "WHERE (:title IS NULL OR LOWER(b.title) LIKE %:title%) " +
            "  AND (:author IS NULL OR LOWER(b.author) LIKE %:author%) " +
            "  AND (:genre IS NULL OR LOWER(b.genre) LIKE %:genre%) " +
            "GROUP BY b.id")
    List<Object[]> searchBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("genre") String genre
    );

}

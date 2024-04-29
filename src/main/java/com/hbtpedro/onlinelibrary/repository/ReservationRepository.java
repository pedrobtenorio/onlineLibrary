package com.hbtpedro.onlinelibrary.repository;
import com.hbtpedro.onlinelibrary.domain.BookCopy;
import com.hbtpedro.onlinelibrary.domain.Reservation;
import com.hbtpedro.onlinelibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUser(User user);
    List<Reservation> findAllByBookCopy(BookCopy bookCopy);
}
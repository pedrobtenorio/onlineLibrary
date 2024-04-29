package com.hbtpedro.onlinelibrary.service;

import com.hbtpedro.onlinelibrary.domain.Book;
import com.hbtpedro.onlinelibrary.domain.BookCopy;
import com.hbtpedro.onlinelibrary.domain.DTO.NewReservationDTO;
import com.hbtpedro.onlinelibrary.domain.Reservation;
import com.hbtpedro.onlinelibrary.domain.User;
import com.hbtpedro.onlinelibrary.domain.enums.CopyStatus;
import com.hbtpedro.onlinelibrary.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final BookCopyService bookCopyService;
    private final BookService bookService;


    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Reservation with id" + id + "not found"));
    }

    public List<Reservation> getReservationByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return reservationRepository.findAllByUser(user);
    }

    public List<Reservation> getReservationByBookCopyId(Long copyId) {
        BookCopy bookCopy = bookCopyService.getBookCopy(copyId);
        return reservationRepository.findAllByBookCopy(bookCopy);
    }
    @Transactional
    public Reservation returnBook(Long id) {
        Reservation reservation = getReservationById(id);
        reservation.setActive(false);
        bookCopyService.makeAvailable(reservation.getBookCopy());
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation saveReservation(NewReservationDTO newReservationDTO) {
        User user = userService.getUserById(newReservationDTO.getUserId());
        Book book = bookService.getBookById(newReservationDTO.getBookId());
        List<BookCopy> bookCopies = bookCopyService.getAllBookCopiesByBook(book);
        BookCopy availableCopy = bookCopies.stream()
                .filter(copy -> copy.getStatus() == CopyStatus.AVAILABLE)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No available copies of the book"));

        Reservation reservation = Reservation.builder()
                .user(user)
                .bookCopy(availableCopy)
                .reservationDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(15))
                .active(true)
                .build();

        bookCopyService.makeBorrowed(availableCopy);

        return reservationRepository.save(reservation);
    }

}

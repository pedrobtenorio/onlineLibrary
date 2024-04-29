package com.hbtpedro.onlinelibrary.controller;

import com.hbtpedro.onlinelibrary.domain.DTO.NewReservationDTO;
import com.hbtpedro.onlinelibrary.domain.Reservation;
import com.hbtpedro.onlinelibrary.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("by-user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationByUserId(@PathVariable Long userId) {
        List<Reservation> reservations = reservationService.getReservationByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("by-copy/{copyId}")
    public ResponseEntity<List<Reservation>> getReservationByCopyId(@PathVariable Long copyId) {
        List<Reservation> reservations = reservationService.getReservationByBookCopyId(copyId);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@RequestBody NewReservationDTO newReservationDTO) {
        Reservation savedReservation = reservationService.saveReservation(newReservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<Reservation> returnBook(@PathVariable Long id) {
        Reservation reservation = reservationService.returnBook(id);
        return ResponseEntity.ok(reservation);
    }


}

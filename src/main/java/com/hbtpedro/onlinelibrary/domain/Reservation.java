package com.hbtpedro.onlinelibrary.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private BookCopy bookCopy;

    private LocalDate reservationDate;
    private LocalDate dueDate;
    private Boolean active;

}

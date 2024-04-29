package com.hbtpedro.onlinelibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hbtpedro.onlinelibrary.domain.enums.CopyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @Enumerated(EnumType.STRING)
    private CopyStatus status;

    @OneToMany(mappedBy = "bookCopy")
    @JsonIgnore
    private List<Reservation> reservations;
}

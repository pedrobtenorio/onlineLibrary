package com.hbtpedro.onlinelibrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String synopsis;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookCopy> copies;

}

package com.JapDetailed.week3_assignment.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Temporal(TemporalType.DATE)  // Ensure it's stored as a date (without time)
    @JsonFormat(pattern = "dd/MM/yyyy") // Specifies the format for the date
    @Column(nullable = false,name = "published_date")
    private LocalDate publishedDate;

    @Column(nullable = false)
    private String isbn;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

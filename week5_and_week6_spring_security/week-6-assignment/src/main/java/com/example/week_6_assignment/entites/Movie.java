package com.example.week_6_assignment.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "movies")
public class Movie extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId; // Unique identifier for the movie

    private String name;     // Name of the movie

    private String genre;    // Genre of the movie (e.g., Action, Comedy, Drama)

    private double rating;   // Rating of the movie (e.g., 8.5/10)

}


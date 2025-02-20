package com.example.week_6_assignment.repositories;

import com.example.week_6_assignment.entites.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // JpaRepository already provides all the CRUD operations by default
}

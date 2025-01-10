package com.JapDetailed.week3_assignment.repositories;

import com.JapDetailed.week3_assignment.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findByName(String name);
}

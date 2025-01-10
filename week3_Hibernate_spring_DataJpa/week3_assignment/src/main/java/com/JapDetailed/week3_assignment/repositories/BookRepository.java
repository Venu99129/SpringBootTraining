package com.JapDetailed.week3_assignment.repositories;

import com.JapDetailed.week3_assignment.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByPublishedDateAfter(LocalDate date);
}

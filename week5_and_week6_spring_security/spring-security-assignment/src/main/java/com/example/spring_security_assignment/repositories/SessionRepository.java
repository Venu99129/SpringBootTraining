package com.example.spring_security_assignment.repositories;

import com.example.spring_security_assignment.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    // Find a session by the user ID
    Optional<SessionEntity> findByUserId(Long userId);

    // You can also use JPQL or native queries to query specific details if needed.
}

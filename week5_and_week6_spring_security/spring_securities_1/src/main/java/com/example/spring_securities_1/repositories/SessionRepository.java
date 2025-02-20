package com.example.spring_securities_1.repositories;

import com.example.spring_securities_1.entities.SessionEntity;
import com.example.spring_securities_1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    // Find a session by the user ID
    List<SessionEntity> findByUserOrderByLastUsedAtAsc(User user);

    Optional<SessionEntity> findByRefreshToken(String refreshToken);

    // You can also use JPQL or native queries to query specific details if needed.
}

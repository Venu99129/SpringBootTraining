package com.example.learnKafka.user_service.Repositories;

import com.example.learnKafka.user_service.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
}

package com.example.spring_securities_1.repositories;

import com.example.spring_securities_1.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<com.example.spring_securities_1.entities.PostEntity, Long> {
}

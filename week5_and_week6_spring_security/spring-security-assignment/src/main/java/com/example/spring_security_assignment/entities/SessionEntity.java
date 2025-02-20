package com.example.spring_security_assignment.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sessions"
//    , uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"userId"})
//}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String token;
}

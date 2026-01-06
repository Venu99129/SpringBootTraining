package com.example.learnKafka.user_service.dtos;

import lombok.Data;

@Data
public class UserRequestDTO {

    private Long id;
    private String name;
    private String email;
}

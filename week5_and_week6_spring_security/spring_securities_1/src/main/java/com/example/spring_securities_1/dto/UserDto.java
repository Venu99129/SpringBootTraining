package com.example.spring_securities_1.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDto {

    @Email
    private String email;

    private String name;

}

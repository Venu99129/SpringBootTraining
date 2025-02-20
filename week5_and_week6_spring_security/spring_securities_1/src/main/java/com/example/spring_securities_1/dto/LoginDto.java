package com.example.spring_securities_1.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    private String email;
    private String password;
}

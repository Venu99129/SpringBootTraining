package com.example.spring_securities_1.entities;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    private String email;
    private String password;
}

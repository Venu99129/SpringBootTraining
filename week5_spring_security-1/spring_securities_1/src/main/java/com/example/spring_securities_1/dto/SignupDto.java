package com.example.spring_securities_1.dto;

import com.example.spring_securities_1.entities.enums.Permission;
import com.example.spring_securities_1.entities.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.Set;

@Data
public class SignupDto {
    @Email
    private String email;

    private String name;

    private String password;

    private Set<Role> roles;
}

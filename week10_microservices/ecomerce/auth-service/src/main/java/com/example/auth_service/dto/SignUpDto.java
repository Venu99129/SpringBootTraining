package com.example.auth_service.dto;

import com.example.auth_service.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {

    private String email;

    private String name;

    private String password;

    private Set<Role> roles;
}

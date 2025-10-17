package com.example.auth_service.dto;

import com.example.auth_service.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class ValidUserResponseDto {
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;
}

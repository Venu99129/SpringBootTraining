package com.example.ecomerce.api_gateway.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class ValidUserResponseDto {
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;
}

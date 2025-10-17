package com.example.ecomerce.order_service.dtos;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class ValidUserResponseDto {
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;
}

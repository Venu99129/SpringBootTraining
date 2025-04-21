package com.example.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
}

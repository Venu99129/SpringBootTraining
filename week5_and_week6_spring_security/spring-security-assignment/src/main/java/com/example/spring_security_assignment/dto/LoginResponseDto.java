package com.example.spring_security_assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private Long id;
    private String accessToken;
    private String refreshToken;
}

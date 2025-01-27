package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.dto.LoginDto;
import com.example.spring_security_assignment.entities.User;
import com.example.spring_security_assignment.repositories.JwtService;
import com.example.spring_security_assignment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String login(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}

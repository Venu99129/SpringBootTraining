package com.example.spring_security_assignment.services;

import com.example.spring_security_assignment.dto.LoginDto;
import com.example.spring_security_assignment.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
   // private final RedisService redisService;
    private final SessionService sessionService;

    public String login(LoginDto loginDto){
        log.info("login method called");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        log.debug("Generated token: {}", token);
        //redisService.setValue(user.getId(), token);

        sessionService.createSession(user.getId(),token);
//        log.info("token generated ");
        return token;
    }
}

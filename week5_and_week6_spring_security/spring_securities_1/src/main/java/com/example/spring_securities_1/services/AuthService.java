package com.example.spring_securities_1.services;

import com.example.spring_securities_1.dto.LoginDto;
import com.example.spring_securities_1.dto.LoginResponseDto;
import com.example.spring_securities_1.entities.User;
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

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;

    public LoginResponseDto login(LoginDto loginDto){
        log.info("login method called");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        log.debug("Generated token: {}", accessToken);
        log.debug("generated refresh token :{}",refreshToken);
        //redisService.setValue(user.getId(), token);
        sessionService.createSession(user,refreshToken);

//        log.info("token generated ");
        return new LoginResponseDto(user.getId(), accessToken,refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {


        Long userId = jwtService.getUserIdFromToken(refreshToken);
        log.info("called refreshToken method in authService get userId :{}",userId);

        sessionService.isValidSession(refreshToken);

        User user = userService.loadUserByUserId(userId);

        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDto(userId,accessToken,refreshToken);
    }
}

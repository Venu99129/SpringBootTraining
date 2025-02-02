package com.example.spring_securities_1.controllers;

import com.example.spring_securities_1.dto.LoginResponseDto;
import com.example.spring_securities_1.dto.SignupDto;
import com.example.spring_securities_1.dto.UserDto;
import com.example.spring_securities_1.dto.LoginDto;
import com.example.spring_securities_1.services.AuthService;
import com.example.spring_securities_1.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignupDto signupDto){
        log.info("creating new user with signup with email :{}",signupDto.getEmail());
        return new ResponseEntity<>(userService.signupUser(signupDto) , HttpStatus.CREATED);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                                                                                 HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
        log.info("in refresh method ");

        String refreshToken = Arrays.stream(request.getCookies()).filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()-> new AuthenticationServiceException("refresh token not found inside the cookies"));

        log.info("called refresh method with refresh token : {}",refreshToken);

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }
}

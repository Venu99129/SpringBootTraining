package com.example.auth_service.controllers;

import com.example.auth_service.dto.LoginDto;
import com.example.auth_service.dto.LoginResponseDto;
import com.example.auth_service.dto.SignUpDto;
import com.example.auth_service.dto.UserDto;
import com.example.auth_service.services.AuthService;
import com.example.auth_service.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping(path = "/free")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpDto signUpDto){
        log.info("creating new user with signup with email :{}",signUpDto.getEmail());
        return userService.signupUser(signUpDto);
    }

    @PostMapping(path = "/login")
    public LoginResponseDto loginUser(@RequestBody LoginDto loginDto, HttpServletRequest request,
                                      HttpServletResponse response){
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return loginResponseDto;
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

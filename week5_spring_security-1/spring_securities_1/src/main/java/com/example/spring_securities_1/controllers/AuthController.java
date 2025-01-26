package com.example.spring_securities_1.controllers;

import com.example.spring_securities_1.dto.SignupDto;
import com.example.spring_securities_1.dto.UserDto;
import com.example.spring_securities_1.entities.LoginDto;
import com.example.spring_securities_1.services.AuthService;
import com.example.spring_securities_1.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignupDto signupDto){
        log.info("creating new user with signup with email :{}",signupDto.getEmail());
        return new ResponseEntity<>(userService.signupUser(signupDto) , HttpStatus.CREATED);
    }

    @PostMapping(path = "login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        log.info("login with email : {}",loginDto.getEmail());
        return ResponseEntity.ok(authService.login(loginDto));

    }
}

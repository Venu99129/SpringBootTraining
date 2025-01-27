package com.example.spring_security_assignment.controllers;

import com.example.spring_security_assignment.dto.LoginDto;
import com.example.spring_security_assignment.dto.SignUpDto;
import com.example.spring_security_assignment.dto.UserDto;
import com.example.spring_security_assignment.repositories.JwtService;
import com.example.spring_security_assignment.services.AuthService;
import com.example.spring_security_assignment.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> SignupUser(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(userService.signUpNewUser(signUpDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}

package com.example.spring_securities_1.controllers;

import com.example.spring_securities_1.dto.UserDto;
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

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto){
        log.info("creating new user with signup with email :{}",userDto.getEmail());
        return new ResponseEntity<>(userService.createNewUser(userDto) , HttpStatus.CREATED);
    }
}

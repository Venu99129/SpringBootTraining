package com.example.auth_service.controllers;

import com.example.auth_service.dto.ValidUserResponseDto;
import com.example.auth_service.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/valid")
@RequiredArgsConstructor
public class ValidController {

    private final ModelMapper modelMapper;

    @PostMapping()
    public ValidUserResponseDto authentication(){
        log.info("token are verified .............!");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //log.info("authentication cookie : {}",authHeader);
        return modelMapper.map(user, ValidUserResponseDto.class);
    }
}

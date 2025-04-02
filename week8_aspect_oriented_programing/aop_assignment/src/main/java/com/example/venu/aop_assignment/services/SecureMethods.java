package com.example.venu.aop_assignment.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecureMethods {

    public void secureMethod1(String jwtToken){
        log.info("successfully jwt token is validated......");
        log.info("very secured method .........");
    }
}

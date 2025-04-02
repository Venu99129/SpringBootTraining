package com.example.venu.aop_assignment.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExceptionThrowService {

    public void throwRunTimeException(String message){
        log.info("throwing exception........");

        throw new RuntimeException(message);
    }
}

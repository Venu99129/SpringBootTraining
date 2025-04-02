package com.example.venu.aop_assignment.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExceptionThrowServiceTest {

    @Autowired
    private ExceptionThrowService exceptionThrowService;

    @Test
    public void testExceptionThrowMethods(){
        exceptionThrowService.throwRunTimeException("for testing validating aop exception handling");
    }

}
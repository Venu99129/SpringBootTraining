package com.example.venu.aop_assignment.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ExceptionHandlingAspect {

    @Pointcut("execution(* com.example.venu.aop_assignment..*(..))")
    public void globalMethodPointCut(){}

    @AfterThrowing(value = "globalMethodPointCut()",throwing = "ex")
    public void handleExceptions(JoinPoint joinPoint, Exception ex){
        log.info("after method call {}",joinPoint.getSignature());
        log.error(ex.getMessage());
    }
}

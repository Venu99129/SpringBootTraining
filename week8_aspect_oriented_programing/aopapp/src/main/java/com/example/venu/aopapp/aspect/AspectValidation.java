package com.example.venu.aopapp.aspect;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class AspectValidation {

    @Pointcut("execution(* com.example.venu.aopapp.services.impl.ShipmentServiceImpl.*(..))")
    public void shipmentsServiceImplementationPointCut(){}


    @Around("shipmentsServiceImplementationPointCut()")
    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object args[] = proceedingJoinPoint.getArgs();

        Long orderId = (Long)args[0];
        if(orderId>0) proceedingJoinPoint.proceed();

        return "Cannot call with negative order Id";
    }
}

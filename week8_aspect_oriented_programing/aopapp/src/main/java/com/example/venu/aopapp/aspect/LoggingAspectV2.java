package com.example.venu.aopapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspectV2 {

    @Pointcut("execution(* com.example.venu.aopapp.services.impl.ShipmentServiceImpl.*(..))")
    public void shipmentsServiceImplementationPointCut(){}

    @Before("shipmentsServiceImplementationPointCut()")
    public void beforeShipmentServiceMethod(JoinPoint joinPoint){
        log.info("before the call method , {}",joinPoint.getSignature());
        log.info("before method kind , {}",joinPoint.getKind());
    }

    @After("shipmentsServiceImplementationPointCut()")
    public void afterShipmentServiceMethod(JoinPoint joinPoint){
        log.info("after the call method , {}",joinPoint.getSignature());
        log.info("after method kind , {}",joinPoint.getKind());
    }

    @AfterReturning(value = "shipmentsServiceImplementationPointCut()", returning = "returnObj")
    public void afterShipmentServiceMethodReturning(JoinPoint joinPoint , Object returnObj){
        log.info("after method returned signature, {}",joinPoint.getSignature());
        log.info("returned Object is , {}",returnObj);
    }

    @Around("shipmentsServiceImplementationPointCut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnedValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long diff = startTime-endTime;

        log.info("Time taken for {} this method is {} ",proceedingJoinPoint.getSignature(),diff);
        return returnedValue;
    }

    @AfterThrowing(value = "shipmentsServiceImplementationPointCut()", throwing = "thrownObj")
    public void afterShipmentServiceMethodThrowing(JoinPoint joinPoint , Object thrownObj){
        log.info("after method returned throwing, {}",joinPoint.getSignature());
        log.info("throwing Object is , {}",thrownObj.toString());
    }
}

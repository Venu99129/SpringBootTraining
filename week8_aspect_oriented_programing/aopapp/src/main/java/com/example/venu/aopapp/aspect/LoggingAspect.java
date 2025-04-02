package com.example.venu.aopapp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("loggingShipmentMethodsExecution()")
    public void beforeShipmentServiceMethods(JoinPoint joinPoint){
        log.info("Before method call : {}",joinPoint.getSignature());
        log.info("before called from LoggingAspect King {} ",joinPoint.getKind());
    }

    @Before("within(com.example.venu.aopapp..*)")
    public void beforeServiceImplementationCalls(){
        log.info("service impl calls");
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalAnnotationCalls(){
        log.info("before Transactional annotation calls");
    }

    @Pointcut("execution(* com.example.venu.aopapp.services.impl.ShipmentServiceImpl.*(..))")
    public void loggingShipmentMethodsExecution(){}

    @After("loggingShipmentMethodsExecution()")
    public void afterShipmentServiceMethods(JoinPoint joinPoint){
        log.info("after method calls signature, {}",joinPoint.getSignature());
    }
}

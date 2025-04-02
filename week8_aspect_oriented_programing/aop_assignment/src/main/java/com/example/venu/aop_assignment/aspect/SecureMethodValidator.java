package com.example.venu.aop_assignment.aspect;


import com.example.venu.aop_assignment.entities.User;
import com.example.venu.aop_assignment.repositories.UserRepository;
import com.example.venu.aop_assignment.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthenticationException;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class SecureMethodValidator {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Pointcut("execution(* com.example.venu.aop_assignment.services.SecureMethods.*(..))")
    public void securedMethodPointCut(){}

    @Before("securedMethodPointCut()")
    public void beforeSecuredMethod(JoinPoint joinPoint){
        log.info("before this method {}",joinPoint.getSignature());
    }
    @After("securedMethodPointCut()")
    public void afterSecuredMethod(JoinPoint joinPoint){
        log.info("after this method {}",joinPoint.getSignature());
    }

    @Around("securedMethodPointCut()")
    public void validateSecuredMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("validating your token..........");
        Object[] args = proceedingJoinPoint.getArgs();

        String jwtToken = (String) args[0];
        Long usedId = jwtService.getUserIdFromToken(jwtToken);

        User user = userRepository.findById(usedId).orElseThrow(()-> new RuntimeException(" user not found with id: "+usedId));
        if(user.getRoles().equals("ADMIN")){
            proceedingJoinPoint.proceed();
            return;
        }
        throw new AuthenticationException("this user doesn't have permission to access");
    }

}

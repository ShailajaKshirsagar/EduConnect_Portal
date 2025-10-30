package com.educonnect.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
//This class is for mthod level logging AOP
public class MethodLoggerAspect {

    @Before("execution(* com.educonnect.controller..*(..)) || execution(* com.educonnect.serviceImpl..*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("Entering : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.educonnect.controller..*(..)) || execution(* com.educonnect.serviceImpl..*(..))")
    public void afterMethod(JoinPoint joinPoint){
        log.info("Completed : {}.{}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.educonnect.controller..*(..)) || execution(* com.educonnect.serviceImpl..*(..))", throwing = "ex")
    public void onError(JoinPoint joinPoint, Throwable ex){
        log.error("Exception in {}.{}(): {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }
}

package com.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.spring.core.loggers.*.logEvent(..))")
    private void allLogEventMethods(){ }

    @Before("allLogEventMethods()")
    private void logBefore(JoinPoint joinPoint){
        System.out.println("BEFORE: " +
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }
}

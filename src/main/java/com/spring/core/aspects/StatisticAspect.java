package com.spring.core.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class StatisticAspect {

    @Pointcut("execution(* com.spring.core.loggers.*.logEvent(..))")
    private void allLogEventMethods() {}

    private Map<Class<?>,Integer> countsOfCalls=new HashMap<>();;

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp){
        Class<?> clazz=jp.getTarget().getClass();
        if(!countsOfCalls.containsKey(clazz)) {
            countsOfCalls.put(clazz, 0);
        }

        countsOfCalls.put(clazz, countsOfCalls.get(clazz)+1);
    }


    public Map<Class<?>,Integer> getStatistic(){
        return countsOfCalls;
    }
}

package com.ms.aspect.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(LogExecution)")
    public Object logAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getName();
        String methodArguments = Arrays.toString(pjp.getArgs());
        MethodSignature methodSignature = (MethodSignature) (pjp.getSignature());
        Method method = methodSignature.getMethod();
        LogExecution logExecution = method.getAnnotation(LogExecution.class);

        if (logExecution.logRequestArguments()) {
            logger.info("Method entry : " + className + ":" + methodName + ":" + methodArguments);
        } else {
            logger.info("Method entry : " + className + ":" + methodName);
        }

        if(logExecution.debugLevel().equals("info")){
            logger.info("Method entry : " + className + ":" + methodName);
        }

        if(logExecution.debugLevel().equals("debug")){
            logger.info("Method entry : " + className + ":" + methodName + ":" + methodArguments);
        }

        Object endArgs = pjp.proceed();

        Long endTime = System.currentTimeMillis();
        Long executionTime = (endTime - startTime);

        if (logExecution.logResponseArguments()) {
            logger.info("Method exit : " + className + ":" + methodName + ":" + endArgs);
        } else {
            logger.info("Method exit : " + className + ":" + methodName);
        }
        logger.info(className + ":" + methodName + " execution completed in : " + executionTime + " ms");
        return endArgs;
    }

    @AfterThrowing(pointcut = "@annotation(LogExecution)", throwing = "ex")
    public void logExceptionAdvice(JoinPoint jp, Exception ex){
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String executionError = ex.getMessage();
        logger.error("Exception occurred at : " + className + ":" + methodName + ":" + executionError);

    }
}

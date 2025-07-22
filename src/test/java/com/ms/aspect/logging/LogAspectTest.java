package com.ms.aspect.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class LogAspectTest {

    @InjectMocks
    LogAspect logAspect;

    @Mock
    ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    MethodSignature signature;

    @Mock
    JoinPoint joinPoint;

    @Test
    void logAdviceTest() throws Throwable {
        Mockito.when(proceedingJoinPoint.proceed()).thenReturn("Test Return Args");
        Mockito.when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("getProduct");
        Mockito.when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"args1", "arg2"});

        TestClass testClass = new TestClass();
        Mockito.when(proceedingJoinPoint.getTarget()).thenReturn(testClass);
        Method method = TestClass.class.getMethod("testMethod1", String.class);
        Mockito.when(signature.getMethod()).thenReturn(method);

        Object endarg = logAspect.logAdvice(proceedingJoinPoint);

        Assertions.assertNotNull(endarg);
        Assertions.assertEquals("Test Return Args", endarg);
    }

    @Test
    void logAdviceTestToNotPrintArgLog() throws Throwable {
        Mockito.when(proceedingJoinPoint.proceed()).thenReturn("Test Return Args");
        Mockito.when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("getProduct");
        Mockito.when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"args1", "arg2"});

        TestClass testClass = new TestClass();
        Mockito.when(proceedingJoinPoint.getTarget()).thenReturn(testClass);
        Method method = TestClass.class.getMethod("testMethod2", String.class);
        Mockito.when(signature.getMethod()).thenReturn(method);

        Object endarg = logAspect.logAdvice(proceedingJoinPoint);

        Assertions.assertNotNull(endarg);
        Assertions.assertEquals("Test Return Args", endarg);
    }

    @Test
    void logExceptionAdviceTest(){
        Mockito.when(joinPoint.getSignature()).thenReturn(signature);
        Mockito.when(signature.getName()).thenReturn("getProductException");
        TestClass testClass = new TestClass();
        Mockito.when(joinPoint.getTarget()).thenReturn(testClass);
        logAspect.logExceptionAdvice(joinPoint, new RuntimeException("AOP caught exception"));
    }


}

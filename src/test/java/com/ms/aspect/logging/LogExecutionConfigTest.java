package com.ms.aspect.logging;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ExtendWith(MockitoExtension.class)
public class LogExecutionConfigTest {

    @Test
    void testLogAspectBeanCreation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LogExecutionConfig.class);
        LogAspect logAspect = context.getBean(LogAspect.class);
        Assertions.assertNotNull(logAspect);
        Assertions.assertTrue(logAspect instanceof LogAspect);
    }
}

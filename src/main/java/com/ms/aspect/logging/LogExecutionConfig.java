package com.ms.aspect.logging;

import org.springframework.context.annotation.Bean;

//@Configuration
public class LogExecutionConfig {

    @Bean
    public LogAspect getLogAspect(){
        return new LogAspect();
    }
}

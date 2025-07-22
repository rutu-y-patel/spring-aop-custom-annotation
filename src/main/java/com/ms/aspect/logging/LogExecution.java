package com.ms.aspect.logging;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecution {
    boolean logRequestArguments() default false;
    boolean logResponseArguments() default false;
    String debugLevel() default "info";

}

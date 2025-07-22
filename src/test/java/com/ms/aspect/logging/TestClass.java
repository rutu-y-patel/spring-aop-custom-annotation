package com.ms.aspect.logging;

public class TestClass {

    @LogExecution(logRequestArguments = true, logResponseArguments = true)
    public String testMethod1(String name){
        return "testAOPMethod";
    }

    @LogExecution(logRequestArguments = false, logResponseArguments = false)
    public String testMethod2(String name){
        return "testAOPMethod";
    }

}

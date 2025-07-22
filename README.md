# 🔍 Spring Boot Custom Annotation Logger with AOP

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This project is a **reusable Spring Boot AOP-based logging and method profiling library** that provides a custom annotation `@LogExecution` to automatically log method entry, exit, arguments, return values, exceptions, and execution time.

---

## 🎯 Features

- Simple `@LogExecution` annotation for any method
- Logs:
    - Method execution time
    - Request arguments (optional)
    - Response values (optional)
    - Exceptions (if thrown)
- Configurable log level: `info`, `debug`
- Includes unit tests
- Can be packaged as a reusable JAR in other Spring Boot apps

---

## 🧪 Annotation Example

```java
@LogExecution(logRequestArguments = true, logResponseArguments = true, debugLevel = "info")
public String processSomething(String input) {
    // your logic
    return "done";
}
```

### 📄 Sample Logs

```
INFO  - Method entry: com.ms.service.OrderService:processOrder:[12345]
INFO  - Method exit: com.ms.service.OrderService:processOrder:Processed
INFO  - com.ms.service.OrderService:processOrder execution completed in: 38 ms
```

---

### 🧨 Exception Logs

```
INFO  - Method entry: com.ms.service.PaymentService:chargeCard:[null]
ERROR - Exception occurred at: com.ms.service.PaymentService:chargeCard:Card number cannot be null
```

---

## ⚙️ How It Works

- Uses `@Around` advice in `LogAspect`
- Reads metadata from `@LogExecution`
- Logs input/output conditionally
- Handles exceptions via `@AfterThrowing`

---

## 🏗️ How to Use

### Option 1: Clone and Run Tests

```bash
git clone https://github.com/rutu-y-patel/spring-aop-custom-annotation.git
cd spring-aop-custom-annotation
mvn test
```

### Option 2: Use as a Library in Other Projects

#### Build the library:

```bash
mvn clean install
```

#### Add dependency in your consuming project:

```xml
<dependency>
  <groupId>com.ms</groupId>
  <artifactId>logging-aspect</artifactId>
  <version>1.0.7</version>
</dependency>
```

---

## 🛠️ Tech Stack

- Java 8+
- Spring Boot 2.7.x
- Spring AOP
- Maven

---

## 📁 Project Structure

```
src/
├── main/java/com/ms/aspect/logging/
│   ├── LogExecution.java
│   ├── LogAspect.java
│   └── LogExecutionConfig.java
├── resources/META-INF/
│   └── spring.factories
├── test/
│   └── (unit tests)
```

---

## 🌐 Related Search Terms (SEO)

This library can also be used as a lightweight **method profiler** in Spring Boot projects.  
It is helpful if you're searching for:

- Spring Boot custom annotation
- Spring AOP logging
- Log method execution time in Java
- Reusable AOP logging library
- Custom annotation logger
- Spring Boot aspect logging
- Java method profiling with AOP

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).  
Use it freely in your projects with proper attribution.
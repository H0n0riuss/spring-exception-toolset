# spring-exception-toolset
Nice and convenient exception handling

## How to
1. Include the dependency in you pom.xml
2. Let Spring Boot scan this package:
```java
 @SpringBootApplication(scanBasePackages = {
         "your.own.package",
         "io.github.honoriuss"})
```

## Features
1. Don`t want to show the requested url in the handling? Override it in you application.yaml:
```yaml 
exception-toolset:
  api-error:
    include-url: false
```

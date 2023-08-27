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
1. You can override (customize) some properties in your application.yaml:
```yaml 
exception-toolset:
  api-error:
    include-url: false
    include-client-info: false
    http-status-code: 400
```

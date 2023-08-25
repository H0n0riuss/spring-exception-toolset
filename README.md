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

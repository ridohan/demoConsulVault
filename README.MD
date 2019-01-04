# Consul & Vault Spring Boot Example


## Consul Configuration 

### 1. Setup Consul Cloud dependency

```xml 
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-config</artifactId>
</dependency>
```


### 2. Setup Consul Configuration
Create an yml file named bootstrap.yml and add the following info for connecting to consul :
```yaml

spring:
  application:
    # Name of your app, will be stored in config/ConsulDemo in consul for example
    name: ConsulDemo
  cloud:
    consul:
      # Host url of consul instance
      host: localhost
      # Host port of consul instance
      port: 8500
      config:
        enabled: true

```
### 3. Retrieve configuration in code
```java
@SpringBootApplication
@RestController
@RefreshScope
public class ConsulVaultDemoApplication   {


    @Value("${foo}")
    private String foo;


    public static void main(String[] args) {
        SpringApplication.run(ConsulVaultDemoApplication.class, args);
    }


    @GetMapping("/foo")
    public String getFoo() {
        return foo;
    }


}
```
The configuration key on Consul would be : config/ConsulDemo/foo

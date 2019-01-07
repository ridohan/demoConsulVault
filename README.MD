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
CF : [ConsulController Class](ConsulController.java)

The configuration key on Consul would be : config/ConsulDemo/foo


## Vault Configuration 

### 1. Setup Vault Cloud dependency

```xml 
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-vault-config</artifactId>
</dependency>
```


### 2. Setup Vault Configuration
Create/Append an yml file named bootstrap.yml and add the following info for connecting to vault :
```yaml

spring:
  application:
    # Name of your app, will be stored in config/ConsulDemo in consul for example
    name: ConsulDemo
  cloud:
    vault:
      # Host url of vault instance
      host: localhost
      # Host port of vault instance
      port: 8200
      # DEV TEST PURPOSE ONLY : we use Root Token and HTTP protocol : THIS IS NOT SECURE
      token: *The root token found when unsealing Vault*
      scheme: http

```
### 3. Retrieve configuration in code
CF : [VaultController Class](VaultController.java)

The configuration key on Consul would be : secret/ConsulDemo

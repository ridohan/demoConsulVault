package com.ridohan.example.consul.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for showcasing Vault capabilities with Spring Boot
 * Every configuration will be found under config/$NAME_OF_APPLICATION/$PROPERTY
 * For foo property will need to be implemented in : config/ConsulDemo/foo
 * */
@RestController
@RefreshScope
public class ConsulController {


    @Value("${foo}")
    private String foo;


    @Value("${properties.consul}")
    private String propertyFromConsulUsedInPropertiesFile;


    @GetMapping("/foo")
    public String getFoo() {
        return foo;
    }


    @GetMapping("/fooFromPropertiesFile")
    public String getFooFromPropertiesFile() {
        return propertyFromConsulUsedInPropertiesFile;
    }

}

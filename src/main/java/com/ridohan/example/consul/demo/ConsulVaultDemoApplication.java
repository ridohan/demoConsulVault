package com.ridohan.example.consul.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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


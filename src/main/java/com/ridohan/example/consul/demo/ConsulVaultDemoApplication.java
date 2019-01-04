package com.ridohan.example.consul.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RefreshScope
public class ConsulVaultDemoApplication   {


    @Autowired
    private VaultTemplate vaultTemplate;


    @Value("${foo}")
    private String foo;

    @Value("${password}")
    String password;

    public static void main(String[] args) {
        SpringApplication.run(ConsulVaultDemoApplication.class, args);
    }


    @GetMapping("/foo")
    public String getFoo() {
        return foo;
    }

    @GetMapping("/password")
    public String getPassword() {
        return password;
    }


    @GetMapping("/writeAndRetrieveCredentials")
    public String writeAndRetrieveCredentials() {
        Credentials credentials = new Credentials("username", "password");
        vaultTemplate.write("secret/test",credentials);
        VaultResponseSupport<Credentials> response = vaultTemplate.read("secret/test",Credentials.class);
        Credentials retrievedCredentials =  response.getData();
        return retrievedCredentials.toString();
    }


}


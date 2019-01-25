package com.ridohan.example.consul.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@RestController
@RefreshScope
/**
 * Controller for showcasing Vault capabilities with Spring Boot
 * Every configuration will be found under secret/$NAME_OF_APPLICATION, thus here it will need to be in secret/ConsulDemo
 */
public class VaultController {

    @Autowired
    private VaultTemplate vaultTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Value("${password}")
    String password;

    @Value("${properties.vault}")
    private String propertyFromVaultUsedInPropertiesFile;

    @GetMapping("/password")
    public String getPassword() {
        return password;
    }


    @GetMapping("/writeAndRetrieveCredentials")
    public String writeAndRetrieveCredentials() {
        Credentials credentials = new Credentials("TOTO", "TATA");
        vaultTemplate.write("secret/test",credentials);
        VaultResponseSupport<Credentials> response = vaultTemplate.read("secret/test",Credentials.class);
        Credentials retrievedCredentials =  response.getData();
        return retrievedCredentials.toString();
    }

    @GetMapping("/passwordFromPropertiesFile")
    public String getFooFromPropertiesFile() {
        return propertyFromVaultUsedInPropertiesFile;
    }


    @GetMapping("/checkDatabaseAccess")
    public String checkDatabaseAccess() {
        List<String> result = jdbcTemplate.queryForList("SHOW DATABASES;",String.class);
        return result.toString();
    }
}

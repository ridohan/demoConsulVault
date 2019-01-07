package com.ridohan.example.consul.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
/**
 * Controller for showcasing Vault capabilities with Spring Boot
 * Every configuration will be found under secret/$NAME_OF_APPLICATION, thus here it will need to be in secret/ConsulDemo
 */
public class VaultController {

    @Autowired
    private VaultTemplate vaultTemplate;


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
        Credentials credentials = new Credentials("username", "password");
        vaultTemplate.write("secret/test",credentials);
        VaultResponseSupport<Credentials> response = vaultTemplate.read("secret/test",Credentials.class);
        Credentials retrievedCredentials =  response.getData();
        return retrievedCredentials.toString();
    }
}

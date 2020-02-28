package com.hutchison.runeshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import javax.security.auth.login.LoginException;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:passwords.properties")
@EntityScan( basePackages = {"com.hutchison.runeshare"})
public class RuneshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuneshareApplication.class, args);
    }

}

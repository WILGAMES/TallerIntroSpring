package org.example.practicaparcial.config;


import org.example.practicaparcial.repositories.RoleRepository;
import org.example.practicaparcial.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfiguration {

    @Bean
    public RoleRepository createRolerRepository() {
        return new RoleRepository();
    }

}


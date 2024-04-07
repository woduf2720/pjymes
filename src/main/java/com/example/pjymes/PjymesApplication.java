package com.example.pjymes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class PjymesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PjymesApplication.class, args);
    }

}

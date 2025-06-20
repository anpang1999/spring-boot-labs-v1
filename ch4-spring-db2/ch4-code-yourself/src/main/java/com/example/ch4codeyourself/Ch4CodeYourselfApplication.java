package com.example.ch4codeyourself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.ch4codeyourself.v5")
@EnableJpaRepositories(basePackages = "com.example.ch4codeyourself.v5")
@EntityScan(basePackages = "com.example.ch4codeyourself.v5")
public class Ch4CodeYourselfApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch4CodeYourselfApplication.class, args);
    }

}

package com.wzl.j8new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.wzl.j8new.*"})
@EnableScheduling
public class J8newApplication {

    public static void main(String[] args) {
        SpringApplication.run(J8newApplication.class, args);
    }

}

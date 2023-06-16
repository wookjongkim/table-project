package com.reservation.tableproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TableProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableProjectApplication.class, args);
    }
}

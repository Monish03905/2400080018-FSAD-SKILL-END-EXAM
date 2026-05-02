package com.klef.fsad.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Entry Point
 * Package : com.klef.fsad.exam
 * Database: fsadendexam
 *
 * On startup, Spring Boot will:
 *  1. Connect to MySQL  (fsadendexam)
 *  2. Auto-create the  'booking' table  (hbm2ddl=update)
 *  3. Run ClientDemo.run() → Insert + HQL View-All
 */
@SpringBootApplication
public class SpringBootHQLApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHQLApplication.class, args);
    }
}

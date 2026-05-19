package com.example.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This is the Spring Boot entry point for the Kafka demo.
 *
 * Run it with:
 * mvn spring-boot:run
 *
 * The app starts an HTTP server on port 8080. You can then call the REST
 * endpoint in KafkaPracticeController to publish a message to Kafka.
 */
@SpringBootApplication
public class KafkaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }
}

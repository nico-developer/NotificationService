package org.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main class to start the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.notification"})
@EnableAsync
public class NotificationSpringApplication {

    /**
     * Main operation.
     *
     * @param args
     *      command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NotificationSpringApplication.class, args);
    }

}

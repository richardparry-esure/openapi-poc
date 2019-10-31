package com.esure.api.openapiexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenApiExampleApplication {
    /**
     * Main class for API Spring application
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpenApiExampleApplication.class);
        app.run(args);
    }
}

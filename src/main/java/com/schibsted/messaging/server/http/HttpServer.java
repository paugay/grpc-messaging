package com.schibsted.messaging.server.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpServer {
    public static void main(String[] args) {
        SpringApplication.run(HttpServer.class, args);
    }
}
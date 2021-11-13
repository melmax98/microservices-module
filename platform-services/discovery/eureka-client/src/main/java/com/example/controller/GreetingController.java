package com.example.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public GreetingController(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @RequestMapping("/greeting")
    public String greeting() {
        return String.format(
                "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }
}

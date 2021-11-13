package com.example.controller;

import com.example.GreetingClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    private final GreetingClient greetingClient;

    public GreetingController(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @RequestMapping("/get-greeting")
    public String greeting(Model model) {
        model.addAttribute("greeting", greetingClient.greeting());
        return "greeting-view";
    }
}

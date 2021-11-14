package com.example.controller;

import com.example.model.Foo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;

@RestController
public class FooController {

    @GetMapping("/foos/{id}")
    public Foo findById(
            @PathVariable long id, HttpServletRequest req, HttpServletResponse res) {
        if (req.getHeader("Test") != null) {
            res.addHeader("Test", req.getHeader("Test"));
        }
        return new Foo(id, randomAlphabetic(4));
    }

}

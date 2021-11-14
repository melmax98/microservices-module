package com.example.controller;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigPropertiesController {

    private DynamicStringProperty propertyOneWithDynamic
            = DynamicPropertyFactory.getInstance()
            .getStringProperty("state", "not found!");

    @GetMapping("/property-from-dynamic-management")
    public String getPropertyValue() {
        return propertyOneWithDynamic.getName() + ": " + propertyOneWithDynamic.get();
    }
}
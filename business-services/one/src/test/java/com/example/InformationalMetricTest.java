package com.example;

import com.netflix.servo.monitor.BasicInformational;
import com.netflix.servo.monitor.MonitorConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InformationalMetricTest {

    @Test
    public void basicInformationTest() {
        BasicInformational informational = new BasicInformational(
                MonitorConfig.builder("test").build());
        informational.setValue("information collected");

        assertEquals("information collected", informational.getValue());
    }
}

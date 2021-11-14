package com.example;

import com.netflix.servo.monitor.BasicCounter;
import com.netflix.servo.monitor.Counter;
import com.netflix.servo.monitor.MonitorConfig;
import com.netflix.servo.monitor.PeakRateCounter;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class CounterTest {

    @Test
    public void basicCounterTest() {
        Counter counter = new BasicCounter(MonitorConfig.builder("test").build());
        assertEquals("counter should start with 0", 0, counter.getValue().intValue());

        counter.increment();

        assertEquals("counter should have increased by 1", 1, counter.getValue().intValue());

        counter.increment(-1);

        assertEquals("counter should have decreased by 1", 0, counter.getValue().intValue());
    }

    @Test
    public void peekRateCounterTest() throws InterruptedException {
        Counter counter = new PeakRateCounter(MonitorConfig.builder("test").build());
        assertEquals(
                "counter should start with 0",
                0, counter.getValue().intValue());

        counter.increment();
        SECONDS.sleep(1);

        counter.increment();
        counter.increment();

        assertEquals("peak rate should have be 2", 2, counter.getValue().intValue());
    }
}

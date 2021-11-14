package example;

import com.netflix.servo.monitor.BasicGauge;
import com.netflix.servo.monitor.Gauge;
import com.netflix.servo.monitor.MaxGauge;
import com.netflix.servo.monitor.MonitorConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GaugeTest {

    @Test
    public void basicGaugeTest() {
        Gauge<Double> gauge = new BasicGauge<>(MonitorConfig.builder("test")
                .build(), () -> 2.32);

        assertEquals(2.32, gauge.getValue(), 0.01);
    }

    @Test
    public void maxGaugeTest() {
        MaxGauge gauge = new MaxGauge(MonitorConfig.builder("test").build());
        assertEquals(0, gauge.getValue().intValue());

        gauge.update(4);
        assertEquals(4, gauge.getCurrentValue(0));

        gauge.update(1);
        assertEquals(4, gauge.getCurrentValue(0));
    }
}

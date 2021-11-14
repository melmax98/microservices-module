package example;

import com.netflix.servo.monitor.BasicTimer;
import com.netflix.servo.monitor.MonitorConfig;
import com.netflix.servo.monitor.StatsTimer;
import com.netflix.servo.monitor.Stopwatch;
import com.netflix.servo.stats.StatsConfig;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class TimerTest {

    @Test
    public void basicTimerTest() throws InterruptedException {
        BasicTimer timer = new BasicTimer(MonitorConfig.builder("test").build(), SECONDS);
        Stopwatch stopwatch = timer.start();

        SECONDS.sleep(1);
        timer.record(2, SECONDS);
        stopwatch.stop();

        assertEquals("timer should count 1 second", 1, timer.getValue().intValue());
        assertEquals("timer should count 3 seconds in total",
                3.0, timer.getTotalTime(), 0.1);
        assertEquals("timer should record 2 updates", 2, timer.getCount().intValue());
        assertEquals("timer should have max 2", 2, timer.getMax(), 0.01);
    }

    @Test
    public void statsTimerTest() throws InterruptedException {
        System.setProperty("netflix.servo", "1000");
        StatsTimer timer = new StatsTimer(MonitorConfig
                .builder("test")
                .build(), new StatsConfig.Builder()
                .withComputeFrequencyMillis(2000)
                .withPercentiles(new double[]{99.0, 95.0, 90.0})
                .withPublishMax(true)
                .withPublishMin(true)
                .withPublishCount(true)
                .withPublishMean(true)
                .withPublishStdDev(true)
                .withPublishVariance(true)
                .build(), SECONDS);
        Stopwatch stopwatch = timer.start();

        SECONDS.sleep(1);
        timer.record(3, SECONDS);
        stopwatch.stop();

        stopwatch = timer.start();
        timer.record(6, SECONDS);
        SECONDS.sleep(2);
        stopwatch.stop();

        assertEquals("timer should count 12 seconds in total",
                12, timer.getTotalTime());
        assertEquals("timer should count 12 seconds in total",
                12, timer.getTotalMeasurement());
        assertEquals("timer should record 4 updates", 4, timer.getCount());
        assertEquals("stats timer value time-cost/update should be 2",
                3, timer.getValue().intValue());
    }

}

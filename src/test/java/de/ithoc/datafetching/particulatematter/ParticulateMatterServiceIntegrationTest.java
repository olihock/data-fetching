package de.ithoc.datafetching.particulatematter;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.repositories.SensorReadingRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParticulateMatterServiceIntegrationTest {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Autowired
    private ParticulateMatterService particulateMatterService;

    @Test
    public void filter_sensor_readings() {
        Instant date = Instant.parse("2022-10-01T00:00:00.00Z");
        int n = 60;
        for (int offset = 1; offset <= n; offset++) {
            sensorReadingRepository.save(createSensorReading(date, offset));
        }
        Instant from = Instant.parse("2022-10-05T02:00:00.00Z");
        Instant to = Instant.parse("2022-10-31T23:59:59.00Z");

        List<SensorReading> fromTo = particulateMatterService.read(from, to);

        Assertions.assertTrue(26 <= fromTo.size());
    }

    private SensorReading createSensorReading(Instant date, long offset) {
        SensorReading sensorReading = new SensorReading();
        sensorReading.setId(offset + 1);

        String dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("UTC")).format(date.plus(offset, ChronoUnit.DAYS));
        sensorReading.setTimestamp(dateStr);

        return sensorReading;
    }

}

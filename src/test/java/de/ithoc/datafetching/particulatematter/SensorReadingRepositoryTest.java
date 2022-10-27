package de.ithoc.datafetching.particulatematter;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.repositories.SensorReadingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SensorReadingRepositoryTest {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Mock
    private SensorReadingRepository sensorReadingRepository;

    @InjectMocks
    private ParticulateMatterService particulateMatterService;

    @Test
    void read_sensor_reading_from_to() {
        List<SensorReading> sensorReadings = new java.util.ArrayList<>(List.of());
        int n = 3000;
        for(int i = 1; i <= n; i++) {
            sensorReadings.add(createSensorReading(i));
        }
        Mockito.when(sensorReadingRepository.findFromTo("2022-10-05 02:00:00", "2022-10-31 23:59:59"))
                .thenReturn(sensorReadings.subList(0, 1000));

        Instant from = Instant.parse("2022-10-05T02:00:00.00Z");
        Instant to = Instant.parse("2022-10-31T23:59:59.00Z");

        List<SensorReading> read = particulateMatterService.read(from, to);

        Assertions.assertEquals(1000, read.size());
    }

    private SensorReading createSensorReading(long offset) {
        SensorReading sensorReading = new SensorReading();
        sensorReading.setId(offset);

        Instant from = Instant.parse("2022-10-01T00:00:00.00Z").plus(offset, ChronoUnit.DAYS);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
                .withZone(ZoneId.of("UTC"));
        String fromStr = dateTimeFormatter.format(from);
        sensorReading.setTimestamp(fromStr);

        return sensorReading;
    }

}
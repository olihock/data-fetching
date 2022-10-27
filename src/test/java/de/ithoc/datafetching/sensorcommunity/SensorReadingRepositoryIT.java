package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.repositories.SensorReadingRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorReadingRepositoryIT {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Test
    public void save_reading() {
        SensorReading sensorReading = new SensorReading();
        sensorReading.setId(1308L);
        sensorReading.setTimestamp("2022-10-13 22:44:00");
        SensorReading save = sensorReadingRepository.save(sensorReading);

        Assertions.assertNotNull(save.getId());
    }

    @Test(expected = JpaSystemException.class)
    public void save_reading_without_id() throws IOException {
        SensorReading sensorReading = new SensorReading();
        sensorReading.setTimestamp("2022-10-13 22:44:00");
        sensorReadingRepository.save(sensorReading);
    }

}
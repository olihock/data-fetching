package de.ithoc.datafetching.sensorcommunity;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.repositories.SensorReadingRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SensorReadingRepositoryIntegrationTest {

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Test
    public void save() {
        SensorReading sensorReading = new SensorReading();
        sensorReading.setId(1308L);
        sensorReading.setTimestamp("2022-10-13 22:44:00");
        SensorReading save = sensorReadingRepository.save(sensorReading);

        Assertions.assertNotNull(save.getTid());
    }

}

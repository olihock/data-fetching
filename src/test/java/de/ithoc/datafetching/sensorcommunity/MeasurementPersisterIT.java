package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasurementPersisterIT {

    @Autowired
    private MeasurementPersister measurementPersister;

    @Test
    public void fetch_airrohr_v1_filter_box_and_save() throws IOException {
        String fileName = "airrohr-v1-filter-box.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json = new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data
                = objectMapper.readValue(json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);

        List<SensorReading> sensorReadings = SensorCommunityMapper.INSTANCE.convert(List.of(data));
        sensorReadings.forEach(sensorReading -> {
            if ("SDS011".equals(sensorReading.getSensor().getSensorType().getName())) {
                measurementPersister.save(sensorReading);
            }
        });
    }

}

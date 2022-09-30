package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MeasurementPersisterTest {

    private static final SensorReading[] sensorReading = new SensorReading[2];

    @Autowired
    private MeasurementPersister measurementPersister;

    @BeforeAll
    static void beforeAll() throws IOException {
        String fileName = "data.dust.min-shortened.json";
        InputStream resourceAsStream = MeasurementPersisterTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] responseData = objectMapper.readValue(
                json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        sensorReading[0] = dozerBeanMapper.map(responseData[0], SensorReading.class);
        sensorReading[1] = dozerBeanMapper.map(responseData[1], SensorReading.class);
    }


    @Test
    void save_single_datum() {
        SensorReading savedSensorReading1 = measurementPersister.save(sensorReading[0]);
        SensorReading savedSensorReading2 = measurementPersister.save(sensorReading[1]);

        Assertions.assertNotNull(savedSensorReading1.getTid());
        Assertions.assertNotNull(savedSensorReading2.getTid());
    }

    @Test
    void save_multiple_data() {
        List<SensorReading> savedData = measurementPersister.save(Arrays.asList(sensorReading));

        Assertions.assertNotNull(savedData);
        Assertions.assertIterableEquals(Arrays.asList(sensorReading), savedData);
    }

}
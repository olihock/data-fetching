package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
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
        sensorReading[0] = SensorCommunityMapper.INSTANCE.convert(responseData[0]);
        sensorReading[1] = SensorCommunityMapper.INSTANCE.convert(responseData[1]);
    }


    @Test
    void save_single_datum() {
    }

    @Test
    void save_multiple_data() {
    }

}
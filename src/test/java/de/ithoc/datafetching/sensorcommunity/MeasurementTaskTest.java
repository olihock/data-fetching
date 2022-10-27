package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MeasurementTaskTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void map_sensor_reading_array() throws IOException {
        String fileName = "data.dust.min.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data
                = objectMapper.readValue(json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);

        List<SensorReading> convertedSensorReadings = SensorCommunityMapper.INSTANCE.convert(List.of(data));

        Assertions.assertEquals(data[0].getId(), convertedSensorReadings.get(0).getId());
    }

    @Test
    public void map_airrohr_v1_filter_box_to_schema() throws IOException {
        String fileName = "airrohr-v1-filter-box.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data
                = objectMapper.readValue(json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);

        List<SensorReading> sensorReadings = SensorCommunityMapper.INSTANCE.convert(List.of(data));

    }

}
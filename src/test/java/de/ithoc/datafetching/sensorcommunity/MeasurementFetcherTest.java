package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class MeasurementFetcherTest {

    private static String measurementJson = null;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MeasurementFetcher measurementFetcher;


    @BeforeAll
    static void beforeAll() throws IOException {
        String fileName = "data.dust.min-shortened.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        measurementJson =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
    }


    @Test
    void load_data_dust_min_shortened() throws JsonProcessingException {
        String url = "https://api.luftdaten.info/static/v2/data.dust.min.json";
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] responseData = objectMapper.readValue(
                measurementJson, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);
        ResponseEntity<de.ithoc.datafetching.sensorcommunity.schema.SensorReading[]> responseEntity
                = new ResponseEntity<>(responseData, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class))
                .thenReturn(responseEntity);

        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data = measurementFetcher.load(url);

        Assertions.assertNotNull(data);
    }

    @Test
    void map_data_json_to_data_entity() throws JsonProcessingException {
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] responseData = objectMapper.readValue(
                measurementJson, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);
        MeasurementFetcher measurementFetcher = new MeasurementFetcher(null, new DozerBeanMapper());
        SensorReading[] modelData = objectMapper.readValue(
                measurementJson, SensorReading[].class);

        SensorReading sensorReading = measurementFetcher.map(responseData[0]);

        Assertions.assertNotNull(sensorReading);
        Assertions.assertEquals(modelData[0], sensorReading);
    }

    @Test
    void load_data_dust_min() throws IOException {
        String fileName = "data.dust.min.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        String url = "https://api.luftdaten.info/static/v2/data.dust.min.json";
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] responseData = objectMapper.readValue(
                json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);
        ResponseEntity<de.ithoc.datafetching.sensorcommunity.schema.SensorReading[]> responseEntity
                = new ResponseEntity<>(responseData, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class))
                .thenReturn(responseEntity);

        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data = measurementFetcher.load(url);

        Assertions.assertNotNull(data);
    }

    @Test
    void load_filter_box_hafencity() throws IOException {
        String fileName = "filter.hafencity.json";
        InputStream resourceAsStream = MeasurementFetcherTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        String url = "https://data.sensor.community/airrohr/v1/filter/box=53.56,10.00,53.58,10.10";
        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] responseData = objectMapper.readValue(
                json, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class);
        ResponseEntity<de.ithoc.datafetching.sensorcommunity.schema.SensorReading[]> responseEntity
                = new ResponseEntity<>(responseData, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, de.ithoc.datafetching.sensorcommunity.schema.SensorReading[].class))
                .thenReturn(responseEntity);

        de.ithoc.datafetching.sensorcommunity.schema.SensorReading[] data = measurementFetcher.load(url);

        Assertions.assertNotNull(data);

    }

}
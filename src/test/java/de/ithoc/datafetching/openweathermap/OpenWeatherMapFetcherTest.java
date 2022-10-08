package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import org.dozer.DozerBeanMapper;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OpenWeatherMapFetcherTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenWeatherMapFetcher openWeatherMapFetcher;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void load() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("weather.json");
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);

        String url = "https://api.openweathermap.org/data/2.5/weather?" +
                "lat=10.05&lon=53.572&units=metric&appid=f40cd1e0ee2afbe3a39fbaf89bcc1fff";
        WeatherReading responseData = objectMapper.readValue(json, WeatherReading.class);
        ResponseEntity<WeatherReading> responseEntity = new ResponseEntity<>(responseData, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(url, WeatherReading.class)).thenReturn(responseEntity);

        WeatherReading data = openWeatherMapFetcher.load(url);

        assertNotNull(data);

        resourceAsStream.close();
    }

    @Test
    public void map() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("weather.json");
        assert resourceAsStream != null;
        String json = new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        WeatherReading responseData = objectMapper.readValue(json, WeatherReading.class);
        OpenWeatherMapFetcher fetcher = new OpenWeatherMapFetcher(null, new DozerBeanMapper());

        de.ithoc.datafetching.openweathermap.model.WeatherReading weatherReading = fetcher.map(responseData);

        assertNotNull(weatherReading);

        resourceAsStream.close();
    }

}
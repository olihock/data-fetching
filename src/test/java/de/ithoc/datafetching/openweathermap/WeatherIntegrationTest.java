package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.model.WeatherReading;
import de.ithoc.datafetching.openweathermap.repositories.WeatherReadingRepository;
import de.ithoc.datafetching.openweathermap.repositories.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherIntegrationTest {

    @Autowired
    private WeatherPersister weatherPersister;

    @Autowired
    private WeatherReadingRepository weatherReadingRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void insert_same_weather_readings_twice() throws IOException {
        WeatherReading weatherReading = getWeatherReading();
        weatherReading.setName("firstWeatherReading");
        WeatherReading sameWeatherReading = getWeatherReading();
        sameWeatherReading.setName("sameWeatherReading");

        WeatherReading savedWeatherReading = weatherPersister.save(weatherReading);
        WeatherReading savedSameWeatherReading = weatherPersister.save(sameWeatherReading);

        Assertions.assertThat(savedSameWeatherReading.getName()).isEqualTo(savedWeatherReading.getName());
    }

    @Test
    public void insert_differnt_weather_readings_with_same_weather() throws IOException {
        WeatherReading weatherReading = getWeatherReading();
        weatherReading.setName("firstWeatherReading");
        WeatherReading sameWeatherReading = getWeatherReading();
        sameWeatherReading.setDt(sameWeatherReading.getDt() + 1);
        sameWeatherReading.setName("sameWeatherReading");

        WeatherReading savedWeatherReading = weatherPersister.save(weatherReading);
        WeatherReading savedSameWeatherReading = weatherPersister.save(sameWeatherReading);

        Assertions.assertThat(savedSameWeatherReading.getName()).isNotEqualTo(savedWeatherReading.getName());
    }

    private WeatherReading getWeatherReading() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("weather.json");
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.openweathermap.schema.WeatherReading weatherReadingSchema = objectMapper.readValue(json, de.ithoc.datafetching.openweathermap.schema.WeatherReading.class);
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        return dozerBeanMapper.map(weatherReadingSchema, WeatherReading.class);
    }

}

package de.ithoc.datafetching.openweathermap;

import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import de.ithoc.datafetching.sensorcommunity.SensorCommunityMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherFetcher {

    private final RestTemplate restTemplate;

    public WeatherFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReading load(String url) {
        ResponseEntity<WeatherReading> response = restTemplate.getForEntity(url, WeatherReading.class);
        return response.getBody();
    }

    public de.ithoc.datafetching.openweathermap.model.WeatherReading map(WeatherReading weatherReading) {
        return SensorCommunityMapper.INSTANCE.convert(weatherReading);
    }

}

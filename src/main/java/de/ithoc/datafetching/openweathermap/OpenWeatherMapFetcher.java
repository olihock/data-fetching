package de.ithoc.datafetching.openweathermap;

import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import de.ithoc.datafetching.sensorcommunity.schema.SensorReading;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class OpenWeatherMapFetcher {

    private final RestTemplate restTemplate;
    private final DozerBeanMapper dozerBeanMapper;

    public OpenWeatherMapFetcher(RestTemplate restTemplate, DozerBeanMapper dozerBeanMapper) {
        this.restTemplate = restTemplate;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public WeatherReading load(String url) {
        ResponseEntity<WeatherReading> response = restTemplate.getForEntity(url, WeatherReading.class);
        return response.getBody();
    }

    public de.ithoc.datafetching.openweathermap.model.WeatherReading map(WeatherReading weatherReading) {
        return dozerBeanMapper.map(
                weatherReading,
                de.ithoc.datafetching.openweathermap.model.WeatherReading.class);
    }

}

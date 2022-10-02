package de.ithoc.datafetching.openweathermap;

import org.dozer.DozerBeanMapper;
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


}

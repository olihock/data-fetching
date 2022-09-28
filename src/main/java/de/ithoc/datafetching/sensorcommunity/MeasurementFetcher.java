package de.ithoc.datafetching.sensorcommunity;

import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MeasurementFetcher {

    private final RestTemplate restTemplate;
    private final DozerBeanMapper dozerBeanMapper;

    public MeasurementFetcher(RestTemplate restTemplate, DozerBeanMapper dozerBeanMapper) {
        this.restTemplate = restTemplate;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public Datum[] load(String url) {
        ResponseEntity<Datum[]> response = restTemplate.getForEntity(url, Datum[].class);
        return response.getBody();
    }

    public de.ithoc.datafetching.sensorcommunity.model.Datum map(Datum datum) {
        return dozerBeanMapper.map(datum, de.ithoc.datafetching.sensorcommunity.model.Datum.class);
    }

}

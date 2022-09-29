package de.ithoc.datafetching.sensorcommunity;

import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

    public String loadRawJson(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public de.ithoc.datafetching.sensorcommunity.model.Datum map(Datum datum) {
        return dozerBeanMapper.map(datum, de.ithoc.datafetching.sensorcommunity.model.Datum.class);
    }

    public List<de.ithoc.datafetching.sensorcommunity.model.Datum> filterBySensorType(
            de.ithoc.datafetching.sensorcommunity.model.Datum[] data, String sensorTypeName) {
        return Arrays.stream(data).filter(
                datum -> sensorTypeName.equals(datum.getSensor().getSensorType().getName())).toList();
    }

}

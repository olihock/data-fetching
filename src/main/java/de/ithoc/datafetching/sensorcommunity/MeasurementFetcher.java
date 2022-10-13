package de.ithoc.datafetching.sensorcommunity;

import de.ithoc.datafetching.sensorcommunity.schema.SensorReading;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MeasurementFetcher {

    private final RestTemplate restTemplate;

    public MeasurementFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SensorReading[] load(String url) {
        ResponseEntity<SensorReading[]> response = restTemplate.getForEntity(url, SensorReading[].class);
        return response.getBody();
    }

    public String loadRawJson(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    public de.ithoc.datafetching.sensorcommunity.model.SensorReading map(SensorReading sensorReading) {
        return SensorCommunityMapper.INSTANCE.convert(sensorReading);
    }

    public List<de.ithoc.datafetching.sensorcommunity.model.SensorReading> filterBySensorType(
            List<de.ithoc.datafetching.sensorcommunity.model.SensorReading> data, String sensorTypeName) {
        return data.stream().filter(
                datum -> sensorTypeName.equals(datum.getSensor().getSensorType().getName())
        ).collect(Collectors.toList());
    }

}

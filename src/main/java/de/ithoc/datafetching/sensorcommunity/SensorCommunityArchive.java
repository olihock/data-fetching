package de.ithoc.datafetching.sensorcommunity;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public record SensorCommunityArchive(RestTemplate restTemplate) {

    public String download(String url) {
        byte[] file = restTemplate.getForObject(url, byte[].class);
        assert file != null;
        return new String(file, StandardCharsets.UTF_8);
    }

    public List<String> filterDates(String htmlContent) {
        return FileLoader.filterDates(htmlContent);
    }

    public List<String> filterCsvFiles(String htmlContent) {
        return FileLoader.filterCsvFiles(htmlContent);
    }

}

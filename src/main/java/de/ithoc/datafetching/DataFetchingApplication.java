package de.ithoc.datafetching;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DataFetchingApplication {

    @Value("${data.sensor.community.filter.box}")
    private String filterBoxUrl;

    @Value("${weather.url}")
    private String wheatherUrl;

    public static void main(String[] args) {
        SpringApplication.run(DataFetchingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String filterBoxUrl() {
        return filterBoxUrl;
    }

    @Bean
    public String weatherUrl() {
        return wheatherUrl;
    }

}

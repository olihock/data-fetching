package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherTask {

    private final String weatherUrl;
    private final OpenWeatherMapFetcher weatherFetcher;
    private final WeatherPersister weatherPersister;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherTask(String weatherUrl, OpenWeatherMapFetcher weatherFetcher, WeatherPersister weatherPersister) {
        this.weatherUrl = weatherUrl;
        this.weatherFetcher = weatherFetcher;
        this.weatherPersister = weatherPersister;
    }

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void fetch() {
        System.out.println("Start weather fetching: " + weatherUrl);

        WeatherReading weatherReading = weatherFetcher.load(weatherUrl);
        de.ithoc.datafetching.openweathermap.model.WeatherReading model = weatherFetcher.map(weatherReading);

        // TODO Persist weather reading

        System.out.println("Weather fetching done");
    }

}

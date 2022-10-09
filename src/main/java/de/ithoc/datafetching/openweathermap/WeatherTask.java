package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherTask {

    private final String weatherUrl;
    private final WeatherFetcher weatherFetcher;
    private final WeatherPersister weatherPersister;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherTask(String weatherUrl, WeatherFetcher weatherFetcher, WeatherPersister weatherPersister) {
        this.weatherUrl = weatherUrl;
        this.weatherFetcher = weatherFetcher;
        this.weatherPersister = weatherPersister;
    }

    @Async
    @Scheduled(cron = "0 0/10 * * * ?")
    public void fetch() {
        System.out.println("Start weather fetching: " + weatherUrl);

        WeatherReading weatherReadingSchema = weatherFetcher.load(weatherUrl);
        de.ithoc.datafetching.openweathermap.model.WeatherReading weatherReadingModel
                = weatherFetcher.map(weatherReadingSchema);

        de.ithoc.datafetching.openweathermap.model.WeatherReading savedWeatherReading
                = weatherPersister.save(weatherReadingModel);

        System.out.println("Weather fetching done: " + savedWeatherReading.getDt());
    }

}

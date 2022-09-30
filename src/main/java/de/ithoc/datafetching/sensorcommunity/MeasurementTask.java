package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementTask {

    private final String filterBoxUrl;
    private final MeasurementFetcher measurementFetcher;
    private final MeasurementPersister measurementPersister;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MeasurementTask(String filterBoxUrl, MeasurementFetcher measurementFetcher,
                           MeasurementPersister measurementPersister) {
        this.filterBoxUrl = filterBoxUrl;
        this.measurementFetcher = measurementFetcher;
        this.measurementPersister = measurementPersister;
    }

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void fetch() throws JsonProcessingException {
        // FIXME Replace system outs by logs
        System.out.println(LocalDateTime.now() + ": Start fetching");
        System.out.println(LocalDateTime.now() + ": " + filterBoxUrl);
        System.out.println(LocalDateTime.now() + " Thread: " + Thread.currentThread() + ", " + Thread.activeCount());

        String rawJson = measurementFetcher.loadRawJson(filterBoxUrl);
       System.out.println(LocalDateTime.now() + " rawJson: " + rawJson.length() + "");

        SensorReading[] data = objectMapper.readValue(rawJson, SensorReading[].class);
        System.out.println(LocalDateTime.now() + " data: " + data);

        List<SensorReading> dataList = measurementFetcher.filterBySensorType(data, "SDS011");
        measurementPersister.save(dataList);
        System.out.println(LocalDateTime.now() + ": Fetching done");
    }

}

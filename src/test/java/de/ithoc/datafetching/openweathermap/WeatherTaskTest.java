package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import de.ithoc.datafetching.sensorcommunity.SensorCommunityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherTaskTest {

    @Mock
    private WeatherFetcher weatherFetcher;

    @Mock
    private WeatherPersister weatherPersister;

    @InjectMocks
    private WeatherTask weatherTask;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void fetch() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("weather.json");
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        resourceAsStream.close();
        WeatherReading schemaReading = objectMapper.readValue(json, WeatherReading.class);
        de.ithoc.datafetching.openweathermap.model.WeatherReading modelReading
                = SensorCommunityMapper.INSTANCE.convert(schemaReading);

        when(weatherFetcher.load(Mockito.any())).thenReturn(schemaReading);
        when(weatherFetcher.map(any(WeatherReading.class))).thenReturn(modelReading);
        when(weatherPersister.save(modelReading)).thenAnswer(reading -> {
            modelReading.setId(1308L);
            return modelReading;
        });

        weatherTask.fetch();

        verify(weatherFetcher).load(any());
        verify(weatherFetcher, times(1)).map(any(WeatherReading.class));
        verify(weatherPersister).save(modelReading);
    }

}
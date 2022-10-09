package de.ithoc.datafetching.openweathermap;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.openweathermap.model.Weather;
import de.ithoc.datafetching.openweathermap.repositories.*;
import de.ithoc.datafetching.openweathermap.schema.WeatherReading;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherPersisterTest {

    @Mock
    private CloudsRepository cloudsRepository;
    @Mock
    private CoordRepository coordRepository;
    @Mock
    private MainRepository mainRepository;
    @Mock
    private SysRepository sysRepository;
    @Mock
    private WeatherReadingRepository weatherReadingRepository;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WindRepository windRepository;

    @InjectMocks
    private WeatherPersister weatherPersister;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("weather-1665310533.json");
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherReading weatherReadingSchema = objectMapper.readValue(json, WeatherReading.class);
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        de.ithoc.datafetching.openweathermap.model.WeatherReading weatherReadingModel
                = dozerBeanMapper.map(
                        weatherReadingSchema, de.ithoc.datafetching.openweathermap.model.WeatherReading.class);
        de.ithoc.datafetching.openweathermap.model.WeatherReading weatherReadingSpy = spy(weatherReadingModel);

        Optional<de.ithoc.datafetching.openweathermap.model.WeatherReading> emptyWeatherReading = Optional.empty();
        when(weatherReadingRepository.findByDt(weatherReadingModel.getDt())).thenReturn(emptyWeatherReading);

        when(weatherRepository.findById(weatherReadingModel.getWeather().get(0).getId())).thenReturn(
                Optional.ofNullable(weatherReadingModel.getWeather().get(0)));
        when(weatherRepository.findById(weatherReadingModel.getWeather().get(1).getId())).thenReturn(
                Optional.ofNullable(weatherReadingModel.getWeather().get(1)));
        /* save must not be called for this setup
        when(weatherRepository.save(weatherReadingModel.getWeather().get(0))).thenReturn(
                weatherReadingModel.getWeather().get(0));
        when(weatherRepository.save(weatherReadingModel.getWeather().get(1))).thenReturn(
                weatherReadingModel.getWeather().get(1));
         */

        when(mainRepository.save(weatherReadingModel.getMain())).thenReturn(weatherReadingModel.getMain());
        doNothing().when(weatherReadingSpy).setMain(weatherReadingModel.getMain());

        when(windRepository.save(weatherReadingModel.getWind())).thenReturn(weatherReadingModel.getWind());
        doNothing().when(weatherReadingSpy).setWind(weatherReadingModel.getWind());

        when(cloudsRepository.save(weatherReadingModel.getClouds())).thenReturn(weatherReadingModel.getClouds());
        doNothing().when(weatherReadingSpy).setClouds(weatherReadingModel.getClouds());

        when(sysRepository.save(weatherReadingModel.getSys())).thenReturn(weatherReadingModel.getSys());
        doNothing().when(weatherReadingSpy).setSys(weatherReadingModel.getSys());

        when(weatherReadingRepository.save(weatherReadingSpy)).thenReturn(weatherReadingModel);

        de.ithoc.datafetching.openweathermap.model.WeatherReading save = weatherPersister.save(weatherReadingSpy);

        Assertions.assertNotNull(save);
        verify(weatherReadingRepository).findByDt(weatherReadingModel.getDt());
        verify(coordRepository).save(weatherReadingModel.getCoord());
        verify(weatherRepository, times(2)).findById(anyLong());
        verify(weatherRepository, never()).save(any(Weather.class));
        verify(mainRepository).save(weatherReadingModel.getMain());
        verify(weatherReadingSpy).setMain(weatherReadingModel.getMain());
        verify(weatherReadingSpy).setWind(weatherReadingModel.getWind());
        verify(weatherReadingSpy).setClouds(weatherReadingModel.getClouds());
        verify(weatherReadingSpy).setSys(weatherReadingModel.getSys());
        verify(weatherReadingRepository).save(weatherReadingSpy);
    }

}
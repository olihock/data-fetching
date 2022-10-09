/*
Copyright 2022 Oliver Hock

Permission is hereby granted, free of charge, to any person obtaining a copy of this software
and associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.ithoc.datafetching.openweathermap;

import de.ithoc.datafetching.openweathermap.model.*;
import de.ithoc.datafetching.openweathermap.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherPersister {

    private final CloudsRepository cloudsRepository;
    private final CoordRepository coordRepository;
    private final MainRepository mainRepository;
    private final SysRepository sysRepository;
    private final WeatherReadingRepository weatherReadingRepository;
    private final WeatherRepository weatherRepository;
    private final WindRepository windRepository;

    public WeatherPersister(
            CloudsRepository cloudsRepository, CoordRepository coordRepository,
            MainRepository mainRepository, SysRepository sysRepository,
            WeatherReadingRepository weatherReadingRepository, WeatherRepository weatherRepository,
            WindRepository windRepository) {
        this.cloudsRepository = cloudsRepository;
        this.coordRepository = coordRepository;
        this.mainRepository = mainRepository;
        this.sysRepository = sysRepository;
        this.weatherReadingRepository = weatherReadingRepository;
        this.weatherRepository = weatherRepository;
        this.windRepository = windRepository;
    }

    @Transactional
    public WeatherReading save(WeatherReading weatherReading) {

        // In case the reading has already been fetched and saved, just return the saved entity and skip the rest.
        Optional<WeatherReading> optionalWeatherReading
                = weatherReadingRepository.findByDt(weatherReading.getDt());
        if (optionalWeatherReading.isPresent()) {
            return optionalWeatherReading.get();
        }

        // Always save coordinates for new weather readings as this is informative and has now ID.
        Coord coord = weatherReading.getCoord();
        coordRepository.save(coord);
        weatherReading.setCoord(coord);

        // Weather entities can be more than one, so walk through to check, save and set them.
        List<Weather> weathers = weatherReading.getWeather();
        List<Weather> collectedWeathers = weathers.stream().map(weather -> {
                    Optional<Weather> foundWeather = weatherRepository.findById(weatherReading.getId());
                    if (foundWeather.isEmpty()) {
                        return weatherRepository.save(weather);
                    }
                    return foundWeather.get();
                }
        ).collect(Collectors.toList());
        weatherReading.setWeather(collectedWeathers);

        // Main contains air readings, and they are individual per reading, so just save them.
        Main main = weatherReading.getMain();
        Main savedMain = mainRepository.save(main);
        weatherReading.setMain(savedMain);

        // Wind contains wind readings, and they are individual per reading, so just save them.
        Wind wind = weatherReading.getWind();
        Wind savedWind = windRepository.save(wind);
        weatherReading.setWind(savedWind);

        // Clouds contains 'some' data, which is individual, so just save it.
        Clouds clouds = weatherReading.getClouds();
        Clouds savedClouds = cloudsRepository.save(clouds);
        weatherReading.setClouds(savedClouds);

        // Same applies for Sys.
        Sys sys = weatherReading.getSys();
        Sys savedSys = sysRepository.save(sys);
        weatherReading.setSys(savedSys);

        // Rest goes in as complete weather reading. Be reminded, it does not exist, yet. That was checked already.
        WeatherReading savedWeatherReading = weatherReadingRepository.save(weatherReading);

        return savedWeatherReading;
    }

}

package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.WeatherReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherReadingRepository extends JpaRepository<WeatherReading, Long> {

    Optional<WeatherReading> findByDt(Long dt);

}

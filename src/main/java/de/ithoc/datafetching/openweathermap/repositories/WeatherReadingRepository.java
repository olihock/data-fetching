package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.WeatherReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherReadingRepository extends JpaRepository<WeatherReading, Long> {
}

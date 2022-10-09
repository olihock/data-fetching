package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long>  {
}

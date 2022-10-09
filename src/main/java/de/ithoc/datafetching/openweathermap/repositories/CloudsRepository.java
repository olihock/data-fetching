package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.Clouds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudsRepository extends JpaRepository<Clouds, Long> {
}

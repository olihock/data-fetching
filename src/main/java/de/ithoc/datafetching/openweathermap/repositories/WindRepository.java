package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.Wind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindRepository extends JpaRepository<Wind, Long> {
}

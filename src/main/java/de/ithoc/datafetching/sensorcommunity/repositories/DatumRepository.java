package de.ithoc.datafetching.sensorcommunity.repositories;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatumRepository extends JpaRepository<SensorReading, Long> {
}

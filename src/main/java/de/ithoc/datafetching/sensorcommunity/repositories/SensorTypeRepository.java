package de.ithoc.datafetching.sensorcommunity.repositories;

import de.ithoc.datafetching.sensorcommunity.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {

    Optional<SensorType> findById(Long id);

}

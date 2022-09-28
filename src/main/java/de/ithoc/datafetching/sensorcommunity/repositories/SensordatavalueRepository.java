package de.ithoc.datafetching.sensorcommunity.repositories;

import de.ithoc.datafetching.sensorcommunity.model.Sensordatavalue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensordatavalueRepository extends JpaRepository<Sensordatavalue, Long> {
}

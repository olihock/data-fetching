package de.ithoc.datafetching.sensorcommunity.repositories;

import de.ithoc.datafetching.sensorcommunity.model.Sensordatavalue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SensordatavalueRepository extends JpaRepository<Sensordatavalue, Long> {
}

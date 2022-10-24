package de.ithoc.datafetching.sensorcommunity.repositories;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    @Query(value = "select sensorReading from SensorReading sensorReading " +
            "where sensorReading.timestamp >= :from and sensorReading.timestamp <= :to"
    )
    List<SensorReading> findFromTo(@Param("from") String from, @Param("to") String to);

}

package de.ithoc.datafetching.particulatematter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticulateMatterRepository extends JpaRepository<Example, Long> {

    @Query(value = "select sensorReading from SensorReading sensorReading " +
            "where sensorReading.timestamp >= :from and sensorReading.timestamp <= :to"
    )
    List<Example> query(@Param("from") String from, @Param("to") String to);

}

package de.ithoc.datafetching.particulatematter;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.repositories.SensorReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ParticulateMatterService {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private final SensorReadingRepository sensorReadingRepository;

    public ParticulateMatterService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public List<SensorReading> read(Instant from, Instant to) {
        String fromStr = DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of("UTC")).format(from);
        String toStr = DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.of("UTC")).format(to);

        return sensorReadingRepository.findFromTo(fromStr, toStr);
    }

}

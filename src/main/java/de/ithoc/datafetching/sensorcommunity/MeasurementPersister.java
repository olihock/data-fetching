package de.ithoc.datafetching.sensorcommunity;

import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import de.ithoc.datafetching.sensorcommunity.model.Location;
import de.ithoc.datafetching.sensorcommunity.model.Sensor;
import de.ithoc.datafetching.sensorcommunity.model.SensorType;
import de.ithoc.datafetching.sensorcommunity.model.Sensordatavalue;
import de.ithoc.datafetching.sensorcommunity.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementPersister {

    private final DatumRepository datumRepository;
    private final LocationRepository locationRepository;
    private final SensorTypeRepository sensorTypeRepository;
    private final SensorRepository sensorRepository;
    private final SensordatavalueRepository sensordatavalueRepository;

    public MeasurementPersister(
            DatumRepository datumRepository, LocationRepository locationRepository,
            SensorTypeRepository sensorTypeRepository, SensorRepository sensorRepository,
            SensordatavalueRepository sensordatavalueRepository) {
        this.datumRepository = datumRepository;
        this.locationRepository = locationRepository;
        this.sensorTypeRepository = sensorTypeRepository;
        this.sensorRepository = sensorRepository;
        this.sensordatavalueRepository = sensordatavalueRepository;
    }

    public SensorReading save(SensorReading sensorReading) {

        Location location = sensorReading.getLocation();
        Location savedLocation = locationRepository.findById(
                location.getId()).orElseGet(() -> locationRepository.save(location));
        sensorReading.setLocation(savedLocation);

        Sensor sensor = sensorReading.getSensor();

        SensorType sensorType = sensor.getSensorType();
        SensorType savedSensorType = sensorTypeRepository.findById(
                sensorType.getId()).orElseGet(() -> sensorTypeRepository.save(sensorType));
        sensor.setSensorType(savedSensorType);

        Sensor savedSensor = sensorRepository.findById(sensor.getId()).orElseGet(() -> sensorRepository.save(sensor));
        sensorReading.setSensor(savedSensor);

        List<Sensordatavalue> sensordatavalues = sensorReading.getSensordatavalues();
        List<Sensordatavalue> savedSensordatavalues = sensordatavalues.stream().map(
                sensordatavalue -> sensordatavalueRepository.findById(
                    sensordatavalue.getId()).orElseGet(() -> sensordatavalueRepository.save(sensordatavalue)))
                .collect(Collectors.toList());
        sensorReading.setSensordatavalues(savedSensordatavalues);

        return datumRepository.save(sensorReading);
    }

    public List<SensorReading> save(List<SensorReading> data) {
        return data.stream().map(this::save).collect(Collectors.toList());
    }

}

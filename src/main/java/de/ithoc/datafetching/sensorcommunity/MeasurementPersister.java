package de.ithoc.datafetching.sensorcommunity;

import de.ithoc.datafetching.sensorcommunity.model.Datum;
import de.ithoc.datafetching.sensorcommunity.model.Location;
import de.ithoc.datafetching.sensorcommunity.model.Sensor;
import de.ithoc.datafetching.sensorcommunity.model.SensorType;
import de.ithoc.datafetching.sensorcommunity.model.Sensordatavalue;
import de.ithoc.datafetching.sensorcommunity.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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

    public Datum save(Datum datum) {

        Location location = datum.getLocation();
        Location savedLocation = locationRepository.findById(
                location.getId()).orElseGet(() -> locationRepository.save(location));
        datum.setLocation(savedLocation);

        Sensor sensor = datum.getSensor();

        SensorType sensorType = sensor.getSensorType();
        SensorType savedSensorType = sensorTypeRepository.findById(
                sensorType.getId()).orElseGet(() -> sensorTypeRepository.save(sensorType));
        sensor.setSensorType(savedSensorType);

        Sensor savedSensor = sensorRepository.findById(sensor.getId()).orElseGet(() -> sensorRepository.save(sensor));
        datum.setSensor(savedSensor);

        List<Sensordatavalue> sensordatavalues = datum.getSensordatavalues();
        List<Sensordatavalue> savedSensordatavalues = sensordatavalues.stream().map(
                sensordatavalue -> sensordatavalueRepository.findById(
                    sensordatavalue.getId()).orElseGet(() -> sensordatavalueRepository.save(sensordatavalue)))
                .toList();
        datum.setSensordatavalues(savedSensordatavalues);

        return datumRepository.save(datum);
    }

    public List<Datum> save(List<Datum> data) {
        return data.stream().map(this::save).toList();
    }

}

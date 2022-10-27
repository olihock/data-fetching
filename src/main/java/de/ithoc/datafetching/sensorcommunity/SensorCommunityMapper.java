package de.ithoc.datafetching.sensorcommunity;

import de.ithoc.datafetching.openweathermap.model.WeatherReading;
import de.ithoc.datafetching.sensorcommunity.model.SensorReading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SensorCommunityMapper {

    SensorCommunityMapper INSTANCE = Mappers.getMapper(SensorCommunityMapper.class);

    SensorReading convert(de.ithoc.datafetching.sensorcommunity.schema.SensorReading sensorReading);
    List<SensorReading> convert(List<de.ithoc.datafetching.sensorcommunity.schema.SensorReading> sensorReadings);

    WeatherReading convert(de.ithoc.datafetching.openweathermap.schema.WeatherReading weatherReading);

}

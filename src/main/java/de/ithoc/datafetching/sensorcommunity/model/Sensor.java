
package de.ithoc.datafetching.sensorcommunity.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Sensor {

    @Id
    private Long id;

    @Column
    private String pin;

    @OneToOne
    private SensorType sensorType;

    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

    public SensorType getSensorType() {
        return sensorType;
    }
    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

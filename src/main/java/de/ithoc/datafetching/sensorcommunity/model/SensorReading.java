
package de.ithoc.datafetching.sensorcommunity.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tid;

    @Column(name = "sensor_reading_id")
    private Long id;

    private String samplingRate;

    @Column
    private String timestamp;

    @OneToOne
    private Location location;

    @OneToOne
    private Sensor sensor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id", referencedColumnName = "tid")
    private List<Sensordatavalue> sensordatavalues = new ArrayList<Sensordatavalue>();

    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSamplingRate() {
        return samplingRate;
    }
    public void setSamplingRate(String samplingRate) {
        this.samplingRate = samplingRate;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public Sensor getSensor() {
        return sensor;
    }
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public List<Sensordatavalue> getSensordatavalues() {
        return sensordatavalues;
    }
    public void setSensordatavalues(List<Sensordatavalue> sensordatavalues) {
        this.sensordatavalues = sensordatavalues;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

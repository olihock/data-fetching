
package de.ithoc.datafetching.sensorcommunity.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "sampling_rate",
    "timestamp",
    "location",
    "sensor",
    "sensordatavalues"
})
@Entity
public class SensorReading {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tid", nullable = false)
    private Long tid;

    @JsonProperty("id")
    @Column
    private Long id;
    @JsonProperty("sampling_rate")
    @Column
    private String samplingRate;
    @JsonProperty("timestamp")
    @Column
    private String timestamp;
    @JsonProperty("location")
    @OneToOne
    private Location location;
    @JsonProperty("sensor")
    @OneToOne
    private Sensor sensor;
    @JsonProperty("sensordatavalues")
    @OneToMany
    private List<Sensordatavalue> sensordatavalues = new ArrayList<Sensordatavalue>();
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("sampling_rate")
    public String getSamplingRate() {
        return samplingRate;
    }

    @JsonProperty("sampling_rate")
    public void setSamplingRate(String samplingRate) {
        this.samplingRate = samplingRate;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("sensor")
    public Sensor getSensor() {
        return sensor;
    }

    @JsonProperty("sensor")
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @JsonProperty("sensordatavalues")
    public List<Sensordatavalue> getSensordatavalues() {
        return sensordatavalues;
    }

    @JsonProperty("sensordatavalues")
    public void setSensordatavalues(List<Sensordatavalue> sensordatavalues) {
        this.sensordatavalues = sensordatavalues;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SensorReading.class.getName()).append('@').append(Long.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("samplingRate");
        sb.append('=');
        sb.append(((this.samplingRate == null)?"<null>":this.samplingRate));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("sensor");
        sb.append('=');
        sb.append(((this.sensor == null)?"<null>":this.sensor));
        sb.append(',');
        sb.append("sensordatavalues");
        sb.append('=');
        sb.append(((this.sensordatavalues == null)?"<null>":this.sensordatavalues));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.sensordatavalues == null)? 0 :this.sensordatavalues.hashCode()));
        result = ((result* 31)+((this.samplingRate == null)? 0 :this.samplingRate.hashCode()));
        result = ((result* 31)+((this.location == null)? 0 :this.location.hashCode()));
        result = ((result* 31)+((this.sensor == null)? 0 :this.sensor.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.timestamp == null)? 0 :this.timestamp.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SensorReading) == false) {
            return false;
        }
        SensorReading rhs = ((SensorReading) other);
        return ((((((((this.sensordatavalues == rhs.sensordatavalues)||((this.sensordatavalues!= null)&&this.sensordatavalues.equals(rhs.sensordatavalues)))&&((this.samplingRate == rhs.samplingRate)||((this.samplingRate!= null)&&this.samplingRate.equals(rhs.samplingRate))))&&((this.location == rhs.location)||((this.location!= null)&&this.location.equals(rhs.location))))&&((this.sensor == rhs.sensor)||((this.sensor!= null)&&this.sensor.equals(rhs.sensor))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.timestamp == rhs.timestamp)||((this.timestamp!= null)&&this.timestamp.equals(rhs.timestamp))));
    }

}

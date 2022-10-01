
package de.ithoc.datafetching.openweathermap.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "speed",
    "deg",
    "gust"
})
@Entity
public class Wind {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tid", nullable = false)
    private Long tid;

    @JsonProperty("speed")
    @Column
    private Double speed;
    @JsonProperty("deg")
    @Column
    private Long deg;
    @JsonProperty("gust")
    @Column
    private Double gust;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @JsonProperty("speed")
    public Double getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @JsonProperty("deg")
    public Long getDeg() {
        return deg;
    }

    @JsonProperty("deg")
    public void setDeg(Long deg) {
        this.deg = deg;
    }

    @JsonProperty("gust")
    public Double getGust() {
        return gust;
    }

    @JsonProperty("gust")
    public void setGust(Double gust) {
        this.gust = gust;
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
        sb.append(Wind.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("speed");
        sb.append('=');
        sb.append(((this.speed == null)?"<null>":this.speed));
        sb.append(',');
        sb.append("deg");
        sb.append('=');
        sb.append(((this.deg == null)?"<null>":this.deg));
        sb.append(',');
        sb.append("gust");
        sb.append('=');
        sb.append(((this.gust == null)?"<null>":this.gust));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.speed == null)? 0 :this.speed.hashCode()));
        result = ((result* 31)+((this.deg == null)? 0 :this.deg.hashCode()));
        result = ((result* 31)+((this.gust == null)? 0 :this.gust.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Wind) == false) {
            return false;
        }
        Wind rhs = ((Wind) other);
        return (((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.speed == rhs.speed)||((this.speed!= null)&&this.speed.equals(rhs.speed))))&&((this.deg == rhs.deg)||((this.deg!= null)&&this.deg.equals(rhs.deg))))&&((this.gust == rhs.gust)||((this.gust!= null)&&this.gust.equals(rhs.gust))));
    }

}


package de.ithoc.datafetching.sensorcommunity.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Sensordatavalue {

    @Id
    private Long id;

    @Column
    private String value;

    @Column
    private String valueType;

    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


package de.ithoc.datafetching.openweathermap.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tid;

    @Column(name = "wheater_id")
    private Long id;

    private String main;
    private String description;
    private String icon;

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

    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

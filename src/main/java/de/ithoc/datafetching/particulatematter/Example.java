package de.ithoc.datafetching.particulatematter;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant timestamp;
//    private Float p1; // SDS011 value PM10
//    private Short windDirection; // in degrees

}

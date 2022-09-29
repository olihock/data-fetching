package de.ithoc.datafetching.sensorcommunity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeasurementTaskTest {

    @Autowired
    private MeasurementTask measurementTask;

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetch() {
        // TODO Write a test case for fetching measurements
    }
}
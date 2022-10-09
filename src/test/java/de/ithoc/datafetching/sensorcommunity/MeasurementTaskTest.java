package de.ithoc.datafetching.sensorcommunity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
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
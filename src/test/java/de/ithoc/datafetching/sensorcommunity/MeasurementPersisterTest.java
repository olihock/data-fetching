package de.ithoc.datafetching.sensorcommunity;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ithoc.datafetching.sensorcommunity.model.Datum;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class MeasurementPersisterTest {

    private static final Datum[] data = new Datum[2];

    @Autowired
    private MeasurementPersister measurementPersister;

    @BeforeAll
    static void beforeAll() throws IOException {
        String fileName = "data.dust.min-shortened.json";
        InputStream resourceAsStream = FileLoaderTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        String json =  new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        de.ithoc.datafetching.sensorcommunity.Datum[] responseData = objectMapper.readValue(
                json, de.ithoc.datafetching.sensorcommunity.Datum[].class);
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        data[0] = dozerBeanMapper.map(responseData[0], Datum.class);
        data[1] = dozerBeanMapper.map(responseData[1], Datum.class);
    }


    @Test
    void save_single_datum() {
        Datum savedDatum1 = measurementPersister.save(data[0]);
        Datum savedDatum2 = measurementPersister.save(data[1]);

        Assertions.assertNotNull(savedDatum1.getTid());
        Assertions.assertNotNull(savedDatum2.getTid());
    }

    @Test
    void testSave() {
    }

}
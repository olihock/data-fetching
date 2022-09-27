package de.ithoc.datafetching.sensorcommunity;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class SensorCommunityArchiveTest {

    @Autowired
    private SensorCommunityArchive sensorCommunityArchive;

    @Test
    @Ignore
    void download_csv_file_as_text() {
        String url = "https://archive.sensor.community/2022-09-26/2022-09-26_bme280_sensor_10006.csv";

        String download = sensorCommunityArchive.download(url);

        Assertions.assertNotNull(download);
    }

    @Test
    void download_and_filter_date_list() throws IOException, URISyntaxException {
        String url = "https://archive.sensor.community/";
        String download = sensorCommunityArchive.download(url);
        List<String> dates = sensorCommunityArchive.filterDates(download);
        Assertions.assertTrue(dates.size() > 0);
    }

}
package de.ithoc.datafetching.sensorcommunity;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void download_and_filter_date_list() {
        String url = "https://archive.sensor.community/";
        String download = sensorCommunityArchive.download(url);
        List<String> dates = sensorCommunityArchive.filterDates(download);
        Assertions.assertTrue(dates.size() > 0);
    }

}
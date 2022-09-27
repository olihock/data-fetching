package de.ithoc.datafetching.sensorcommunity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileLoaderTest {

    private String read(String fileName) throws IOException, URISyntaxException {
        InputStream resourceAsStream = FileLoaderTest.class.getClassLoader().getResourceAsStream(fileName);
        assert resourceAsStream != null;
        return new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    @Test
    void read_and_filter_dates_from_list() throws IOException, URISyntaxException {
        String read = read("date-list.html");
        Assertions.assertNotNull(read);
        List<String> strings = FileLoader.filterDates(read);
        Assertions.assertEquals(2541, strings.size());
    }

    @Test
    void read_and_filter_csv_tags_from_list() throws IOException, URISyntaxException {
        String read = read("csv-list.html");
        Assertions.assertNotNull(read);
        List<String> strings = FileLoader.filterCsvFiles(read);
        Assertions.assertEquals(7, strings.size());
    }

    @Test
    void read_csv_from_subfolder() throws IOException, URISyntaxException {
        String read = read("2015-10-01/2015-10-01_ppd42ns_sensor_27.csv");
        Assertions.assertNotNull(read);
    }

    @Test
    void download_csv_files() throws IOException, URISyntaxException {
        String read = read("date-list-shortened.html");
        List<String> strings = FileLoader.filterDates(read);
        Assertions.assertEquals(3, strings.size());

        strings.forEach(date -> {
            try {
                String fileName = date + ".html";
                String content = read(fileName);
                List<String> csvFiles = FileLoader.filterCsvFiles(content);
                Assertions.assertTrue(csvFiles.size() > 0);

                csvFiles.forEach(csv -> {
                    String file = date + "/" + csv;
                    try {
                        String csvContent = read(file);
                        Assertions.assertNotNull(csvContent);

                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

}
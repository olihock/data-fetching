package de.ithoc.datafetching.sensorcommunity;

import java.util.Arrays;
import java.util.List;

public class FileLoader {

    public static List<String> filterDates(String content) {
        String[] split = content.split(System.lineSeparator());
        return getTags(split);
    }

    public static List<String> filterCsvFiles(String htmlContent) {
        String[] split = htmlContent.split(System.lineSeparator());
        List<String> tags = getTags(split);
        return tags.subList(1, tags.size());
    }

    private static List<String> getTags(String[] split) {
        List<String> list = Arrays.asList(split);
        return list.stream().filter(line -> line.contains(">20")).map(line -> {
            int startIndex = line.indexOf(">");
            int endIndex = line.lastIndexOf("<");
            return line.substring(startIndex + 1, endIndex);
        }).toList();
    }

}



package util_information.search_value;

import logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SearchValueFile {

    public static double doSearchValueFile (File file) {

        Pattern pattern = Pattern.compile("[0-9]+(.{0,1})([0-9]+)");

        Stream<String> stream = null;
        try {
            stream = Files.lines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Logger.executionLogger(new Date(), "Filtration");

        List<String> result  = stream
                .filter(line -> line.toLowerCase().contains("total"))
                .toList();

        String string = result.toString();

        Logger.executionLogger(new Date(), "Replacing commas with dots and displaying values");

        if (string.contains(",")) {
            String newString = string.replace(",", ".");
            Matcher matcher = pattern.matcher(newString);

            if (matcher.find()) {
                return Double.parseDouble(matcher.group());
            }

        } else {
            Matcher matcher = pattern.matcher(string);

            if (matcher.find()) {
                return Double.parseDouble(matcher.group());
            }
        }

        return 0;

    }

}

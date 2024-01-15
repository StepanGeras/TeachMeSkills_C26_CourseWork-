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
import static util_information.constant.Constant.*;

//search value in file

public class SearchValueFile {

    public static double doSearchValueFile (File file) {

        Pattern pattern = Pattern.compile("[0-9]+(.{0,1})([0-9]+)");

        Stream<String> stream = null;
        try {
            stream = Files.lines(file.toPath());
        } catch (IOException e) {
            Logger.errorLogger(new Date(), "Error IOException", e);
            throw new RuntimeException(e);
        }

        Logger.executionLogger(new Date(), "Filtration file");

        List<String> result  = stream
                .filter(line -> line.toLowerCase().contains("total"))
                .toList();

        String string = result.toString();

        Logger.executionLogger(new Date(), "Replacing commas with dots and displaying values");
        Matcher matcher = pattern.matcher(string);

        if (string.contains(",")) {
            Logger.executionLogger(new Date(), "Replacing a comma with a dot");
            String newString = string.replace(",", ".");
            Matcher newMatcher = pattern.matcher(newString);

            Logger.executionLogger(new Date(), "Search for value");

            if (newMatcher.find()) {
                if (newString.contains("EURO")) {
                    Logger.executionLogger(new Date(), "Currency of this file is EURO");
                    return Math.round((Double.parseDouble(newMatcher.group()) * EURO_DOLLAR) * 100.0) / 100.0;
                } else if (newString.contains("GBR")) {
                    Logger.executionLogger(new Date(), "Currency of this file is GBR");
                    return Math.round((Double.parseDouble(newMatcher.group()) * GBR_DOLLAR) * 100.0) / 100.0;
                } else {
                    Logger.executionLogger(new Date(), "Currency of this file is Dollar");
                    return Math.round((Double.parseDouble(newMatcher.group())) * 100.0) / 100.0;
                }
            }

        } else {

            if (matcher.find()) {
                if (string.contains("EURO")) {
                    Logger.executionLogger(new Date(), "Currency of this file is EURO");
                    return Math.round((Double.parseDouble(matcher.group()) * EURO_DOLLAR) * 100.0) / 100.0;
                } else if (string.contains("GBR")) {
                    Logger.executionLogger(new Date(), "Currency of this file is GBR");
                    return Math.round((Double.parseDouble(matcher.group()) * GBR_DOLLAR) * 100.0) / 100.0;
                } else {
                    Logger.executionLogger(new Date(), "Currency of this file is Dollar");
                    return Math.round((Double.parseDouble(matcher.group())) * 100.0) / 100.0;
                }
            }

        }

        stream.close();

        return 0;

    }

}

package file_parsing;

import logger.Logger;
import util_information.search_value.SearchValueFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import static util_information.constant.Constant.*;

public class CreateStatistics extends SimpleFileVisitor<Path> {

    double SUM_INVOICE = 0;
    double SUM_CHECK = 0;
    double SUM_ORDER = 0;

/**
 * service for recording and moving a file
 */

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        try {

            double allSum = SUM_CHECK + SUM_ORDER + SUM_INVOICE;

            Logger.executionLogger(new Date(), "File check: " + file.getFileName());

            if (((file.getFileName().toString().toLowerCase().startsWith(YEAR_FILE_NAME + START_FILE_NAME_CHECK)) && file.getFileName().toString().endsWith(FILE_TYPE))) {

                Logger.executionLogger(new Date(), "File: " + file.getFileName() + " - valid");

                SUM_CHECK += SearchValueFile.doSearchValueFile(file.toFile());

                Logger.executionLogger(new Date(), "Recording the total turnover of checks in a file");
                Files.write(Paths.get(PATH_RESULT_FILE_CHECK), ("Total turnover for all checks: " + SUM_CHECK).getBytes());

            } else if ((file.getFileName().toString().toLowerCase().startsWith(YEAR_FILE_NAME + START_FILE_NAME_ORDER) && file.getFileName().toString().endsWith(FILE_TYPE))) {

                Logger.executionLogger(new Date(), "File: " + file.getFileName() + " - valid");

                SUM_ORDER += SearchValueFile.doSearchValueFile(file.toFile());

                Logger.executionLogger(new Date(),  "Recording the total turnover of order in a file");
                Files.write(Paths.get(PATH_RESULT_FILE_ORDER), ("Total turnover for all orders: " + SUM_ORDER).getBytes());

            } else if ((file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_INVOICE) && file.getFileName().toString().endsWith(YEAR_FILE_NAME + FILE_TYPE))) {

                Logger.executionLogger(new Date(), "File: " + file.getFileName() + " - valid");

                SUM_INVOICE += SearchValueFile.doSearchValueFile(file.toFile());

                Logger.executionLogger(new Date(), "Recording the total turnover of invoice in a file");
                Files.write(Paths.get(PATH_RESULT_FILE_INVOICE), ("Total turnover for all invoices: " + SUM_INVOICE).getBytes());

            } else {
                try {
                    Logger.executionLogger(new Date(), "File named " + file.getFileName() + " is not valid");
                    Logger.executionLogger(new Date(), "Moving a file");
                    Files.move(file, Paths.get(PATH_INVALID_FILE_DIRECTORY + "/" + file.getFileName()));
                    Logger.executionLogger(new Date(), "File " + file.getFileName() + " transfer was successful");
                } catch (IOException e) {
                    Logger.errorLogger(new Date(), "Failed to move file", e);
                }
            }

            Logger.executionLogger(new Date(), "Recording total turnover in a file");
            Files.write(Paths.get(PATH_ALL_RESULT_FILE), ("Total turnover " + allSum).getBytes());

        } catch (IOException e) {
            Logger.errorLogger(new Date(), "Write error", e);
        }

        return FileVisitResult.CONTINUE;
    }
}

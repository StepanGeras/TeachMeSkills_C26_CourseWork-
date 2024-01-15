package parsing_file.create_statistics;

import logger.Logger;
import util_information.search_value.SearchValueFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import static util_information.constant.Constant.*;

public class CreateStatistics extends SimpleFileVisitor<Path> {


/**
 * service for recording and moving a file
 */

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        Logger.executionLogger(new Date(), "File check: " + file.getFileName());

        if (((file.getFileName().toString().toLowerCase().startsWith(YEAR_FILE_NAME + START_FILE_NAME_CHECK)) && file.getFileName().toString().endsWith(FILE_TYPE))) {

            checkCheck(file.toFile());

        } else if ((file.getFileName().toString().toLowerCase().startsWith(YEAR_FILE_NAME + START_FILE_NAME_ORDER) && file.getFileName().toString().endsWith(FILE_TYPE))) {

            checkOrder(file.toFile());

        } else if ((file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_INVOICE) && file.getFileName().toString().endsWith(YEAR_FILE_NAME + FILE_TYPE))) {

            checkInvoice(file.toFile());

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

        return FileVisitResult.CONTINUE;

    }

    private static void checkCheck (File file) {

        Logger.executionLogger(new Date(), "File: " + file.getName() + " - valid");

        SUM_CHECK += SearchValueFile.doSearchValueFile(file);

    }

    private static void checkInvoice (File file) {

        Logger.executionLogger(new Date(), "File: " + file.getName() + " - valid");

        SUM_INVOICE += SearchValueFile.doSearchValueFile(file);

    }

    private static void checkOrder (File file) {

        Logger.executionLogger(new Date(), "File: " + file.getName() + " - valid");

        SUM_ORDER += SearchValueFile.doSearchValueFile(file);

    }



}

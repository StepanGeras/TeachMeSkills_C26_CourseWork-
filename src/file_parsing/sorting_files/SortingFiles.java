package file_parsing.sorting_files;

import logger.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class SortingFiles extends SimpleFileVisitor<Path> {

    String START_FILE_NAME_CHECK = "2023_electric_bill";
    String START_FILE_NAME_INVOICE = "invoice";
    String CONTENT_YEAR_FILE_NAME_INVOICE = "2023";
    String START_FILE_NAME_ORDER = "2023_order";
    String MOVE_PATH_INVALID_FILE = "/Users/stepan_gerasimovich/Desktop/Курсач/TeachMeSkills_C26_CourseWork/file_invalid/";

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        if (((!file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_CHECK)) || !file.getFileName().toString().endsWith(".txt")) &&
                (!file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_ORDER) || !file.getFileName().toString().endsWith(".txt")) &&
                (!file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_INVOICE) || !file.getFileName().toString().endsWith(".txt") && !file.getFileName().toString().contains(CONTENT_YEAR_FILE_NAME_INVOICE))) {
                    try {
                        Files.move(file, Paths.get(MOVE_PATH_INVALID_FILE + file.getFileName()));
                        Logger.executionLogger(new Date(), "File named " + file.getFileName() + " is not valid");
                    } catch (IOException e) {
                        Logger.errorLogger(new Date(), "Failed to move file", e);
                    }
                } else {
            Logger.executionLogger(new Date(), "File named " + file.getFileName() + " is valid");
        }

        return FileVisitResult.CONTINUE;

    }

}

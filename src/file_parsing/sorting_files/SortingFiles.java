package file_parsing.sorting_files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class SortingFiles extends SimpleFileVisitor<Path> {

    String START_FILE_NAME_CHECK = "2023_electric_bill";
    String START_FILE_NAME_INVOICE = "invoice";
    String START_FILE_NAME_ORDER = "2023_order";
    String MOVE_PATH_INVALID_FILE = "/Users/stepan_gerasimovich/Desktop/Курсач/TeachMeSkills_C26_CourseWork/file_invalid/";

    @Override
    public java.nio.file.FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        if((file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_CHECK)) && file.getFileName().toString().endsWith(".txt") ||
                file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_ORDER) && file.getFileName().toString().endsWith(".txt")  ||
                file.getFileName().toString().toLowerCase().startsWith(START_FILE_NAME_INVOICE) && file.getFileName().toString().endsWith(".txt") && file.getFileName().toString().contains("2023")){
        } else {
            try {
                Files.move(file, Paths.get(MOVE_PATH_INVALID_FILE + file.getFileName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return java.nio.file.FileVisitResult.CONTINUE;

    }

}

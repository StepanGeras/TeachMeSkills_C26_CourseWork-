package creating_directory_file;

import create_statistics.CreateStatistics;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static util_information.constant.Constant.*;

public class CreatingDirectoryFile {

    public static void doCreatingDirectory (Path path) {

        Logger.executionLogger(new Date(), "Checking for directory existence (file invalid)");
        if (!Files.isDirectory(Paths.get(PATH_INVALID_FILE_DIRECTORY))) {
            try {
                Logger.executionLogger(new Date(), "Creating a directory (file invalid)");
                Files.createDirectory(Paths.get(PATH_INVALID_FILE_DIRECTORY));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating directory (file_invalid)", e);
            }
        }

        Logger.executionLogger(new Date(), "Checking for directory existence (result)");
        if (!Files.isDirectory(Paths.get(PATH_RESULT_DIRECTORY))) {
            try {
                Logger.executionLogger(new Date(), "Creating a directory (result)");
                Files.createDirectory(Paths.get(PATH_RESULT_DIRECTORY));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating directory (result)", e);
            }
        }

        doCreatingFile(path);

    }

    public static void doCreatingFile (Path path) {

        Logger.executionLogger(new Date(), "Check for file existence (all result)");
        if (!Files.isRegularFile(Paths.get(PATH_ALL_RESULT_FILE))) {
            try {
                Logger.executionLogger(new Date(), "Creating a file (all result)");
                Files.createFile(Paths.get(PATH_ALL_RESULT_FILE));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating file (all result)", e);
            }
        }

        Logger.executionLogger(new Date(), "Check for file existence (result check)");
        if (!Files.isRegularFile(Paths.get(PATH_RESULT_FILE_CHECK))) {
            try {
                Logger.executionLogger(new Date(), "Creating a file (result check)");
                Files.createFile(Paths.get(PATH_RESULT_FILE_CHECK));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating file (result check)", e);
            }
        }

        Logger.executionLogger(new Date(), "Check for file existence (result invoice)");
        if (!Files.isRegularFile(Paths.get(PATH_RESULT_FILE_INVOICE))) {
            try {
                Logger.executionLogger(new Date(), "Creating a file (result invoice)");
                Files.createFile(Paths.get(PATH_RESULT_FILE_INVOICE));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating file (result invoice)", e);
            }
        }

        Logger.executionLogger(new Date(), "Check for file existence (result order)");
        if (!Files.isRegularFile(Paths.get(PATH_RESULT_FILE_ORDER))) {
            try {
                Logger.executionLogger(new Date(), "Creating a file (result order)");
                Files.createFile(Paths.get(PATH_RESULT_FILE_ORDER));
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating file (result order)", e);
            }
        }


        Logger.executionLogger(new Date(), "Collection of statistics");
        try {
            Files.walkFileTree(path, new CreateStatistics());
        } catch (IOException e) {
            Logger.errorLogger(new Date(), "Error sorting file", e);
        }
        Logger.executionLogger(new Date(), "Statistics collection was successful");

    }

}

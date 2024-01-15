package creating_directory_file;

import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static util_information.constant.Constant.*;

public class CreatingDirectoryFile {

    public static void doCreating () {

        doCreatingDirectory(PATH_INVALID_FILE_DIRECTORY, "file invalid");
        doCreatingDirectory(PATH_RESULT_DIRECTORY, "result");
        doCreatingFile(PATH_ALL_RESULT_FILE, "all result");

    }

    private static void doCreatingDirectory (String string, String name) {

        Logger.executionLogger(new Date(), "Checking for directory existence (" + name + ")");
        Path path = Paths.get(string);
        if (!Files.isDirectory(path)) {
            try {
                Logger.executionLogger(new Date(), "Creating a directory (" + name + ")");
                Files.createDirectory(path);
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating directory (" + name + ")" , e);
            }
        }

    }

    private static void doCreatingFile (String string, String name) {

        Logger.executionLogger(new Date(), "Check for file existence (" + name + ")");
        Path path = Paths.get(string);
        if (!Files.isRegularFile(path)) {
            try {
                Logger.executionLogger(new Date(), "Creating a file (" + name + ")");
                Files.createFile(path);
                Logger.executionLogger(new Date(), "Creation was successful");
            } catch (IOException e) {
                Logger.errorLogger(new Date(), "Error creating file (" + name + ")", e);
            }
        }

    }

}

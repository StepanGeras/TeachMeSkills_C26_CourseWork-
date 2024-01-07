package util_information.path_check;

import exception.WrongPathException;
import file_parsing.sorting_files.SortingFiles;
import logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class PathCheck {

    public static void pathFileCheck  () {

        Logger.executionLogger(new Date(), "Check path to the directory");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the directory");
        String pathFile = scanner.nextLine();

        try {
            if (Files.exists(Paths.get(pathFile))) {
                Logger.executionLogger(new Date(), "Verification was successful");
                Files.walkFileTree(Paths.get(pathFile), new SortingFiles());
                Logger.executionLogger(new Date(), "File processing was successful");
            } else {
                throw new WrongPathException(20, "Wrong path");
            }
        } catch (WrongPathException e) {
            Logger.errorLogger(new Date(), "Error path directory", e);
        } catch (IOException e) {
            Logger.errorLogger(new Date(), "Error sorting file", e);
        }

    }

}

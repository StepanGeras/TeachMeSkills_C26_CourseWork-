package util_information.path_check;

import exception.WrongPathException;
import exception.WrongSessionException;
import file_description.CreatingDirectoryFile;
import logger.Logger;
import session.Session;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class PathCheck {

    public static void pathFileCheck  (Session session) {

        try {
            if (session != null) {
                try {
                    if (session.sessionAlive()) {
                        Logger.executionLogger(new Date(), "Check path to the directory");

                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the path to the directory");
                        String pathFile = scanner.nextLine();
                        Path path = Paths.get(pathFile);
                        File file = new File(path.toUri());

                        try {
                            if (file.exists() && file.isDirectory()) {
                                Logger.executionLogger(new Date(), "Verification was successful");
                                CreatingDirectoryFile.doCreatingDirectory(path);
                                Logger.executionLogger(new Date(), "File processing was successful");
                            } else if (file.exists() && !file.isDirectory()) {
                                Logger.executionLogger(new Date(), "Directory is empty");
                                throw new WrongPathException("Directory is empty");
                            } else if (!file.exists()) {
                                Logger.executionLogger(new Date(), "Path does not exist");
                                throw new WrongPathException("Path does not exist");
                            }
                        } catch (WrongPathException e) {
                            Logger.errorLogger(new Date(), "Error path directory", e);
                        }
                    } else {
                        Logger.executionLogger(new Date(), "Session time has ended");
                        throw new WrongSessionException("");
                    }
                } catch (WrongSessionException e) {
                    Logger.errorLogger(new Date(), "Session time has expired", e);
                }
            } else {
                Logger.executionLogger(new Date(), "Session dead");
                throw new WrongSessionException("Wrong session");
            }
        } catch (WrongSessionException e) {
            Logger.errorLogger(new Date(), "Error session", e);
        }

    }

}

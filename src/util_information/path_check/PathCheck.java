package util_information.path_check;

import exception.WrongPathException;
import exception.WrongSessionException;
import file_parsing.sorting_files.SortingFiles;
import logger.Logger;
import session_description.session_time.Session;

import java.io.IOException;
import java.nio.file.Files;
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

                        try {
                            if (Files.exists(Paths.get(pathFile))) {
                                Logger.executionLogger(new Date(), "Verification was successful");
                                Files.walkFileTree(Paths.get(pathFile), new SortingFiles());
                                Logger.executionLogger(new Date(), "File processing was successful");
                            } else {
                                throw new WrongPathException("Wrong path");
                            }
                        } catch (WrongPathException e) {
                            Logger.errorLogger(new Date(), "Error path directory", e);
                        } catch (IOException e) {
                            Logger.errorLogger(new Date(), "Error sorting file", e);
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

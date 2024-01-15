package util_information.path_check;

import exception.WrongPathException;
import exception.WrongSessionException;
import creating_directory_file.CreatingDirectoryFile;
import logger.Logger;
import session.Session;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

import static util_information.constant.Constant.PATH_FIN_FILE;

//path check 

public class PathCheck {

    public static Path pathFileCheck  (Session session) {

       try {
            if (session != null) {
                try {
                    if (session.sessionAlive()) {

                        Logger.executionLogger(new Date(), "Check path to the directory");

                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Enter the path to the directory");
                        String pathFile = scanner.nextLine();
                        Path path = Paths.get(pathFile);

                        try {
                            if (path.equals(Paths.get(PATH_FIN_FILE))) {

                                Logger.executionLogger(new Date(), "Verification was successful");
                                CreatingDirectoryFile.doCreating();
                                Logger.executionLogger(new Date(), "File processing was successful");

                                return path;

                            } else {
                                throw new WrongPathException("Wrong path");
                            }
                        } catch (WrongPathException e) {
                            Logger.errorLogger(new Date(), "Error path directory", e);
                            System.out.println("You entered an incorrect folder path");
                            return null;
                        }

                    } else {
                        Logger.executionLogger(new Date(), "Session time has ended");
                        throw new WrongSessionException("Session time has ended");
                    }
                } catch (WrongSessionException e) {
                    Logger.errorLogger(new Date(), "Session time has expired", e);
                    System.out.println("Session time has expired");
                    return null;
                }
            } else {
                Logger.executionLogger(new Date(), "Session dead");
                throw new WrongSessionException("Wrong session");
            }
       } catch (WrongSessionException e) {
           Logger.errorLogger(new Date(), "Error session", e);
           System.out.println("Error session");
           return null;
       }

    }

}

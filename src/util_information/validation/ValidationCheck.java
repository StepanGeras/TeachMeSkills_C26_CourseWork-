package util_information.validation;

import exception.WrongLoginException;
import exception.WrongPasswordException;
import logger.Logger;
import session_description.information.Information;
import util_information.encryption.Encryption;
import util_information.path_check.PathCheck;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {

    static Scanner SCANNER = new Scanner(System.in);
    static Pattern PATTERN = Pattern.compile("^([0-9a-zA-Z_]{6,14})$");
    static Information INFORMATION = new Information();

    public static void doValidationCheckLogin() {

        Logger.executionLogger(new Date(), "Check login");

        System.out.println("Enter the login");
        String login = SCANNER.nextLine();
        Matcher matcher = PATTERN.matcher(login);

        try {
            if (!matcher.find() || !login.equals(Encryption.deCoding(INFORMATION.getLogin()))) {
                for (int i = 2; i >= 0; i--) {
                    if (i == 0) {
                        throw new WrongLoginException(14, "Wrong login");
                    }
                    Logger.executionLogger(new Date(), "Wrong login \n" + "Attempts left " + i);
                    System.out.println("Wrong login \n" + "Attempts left " + i);
                }
            }
            Logger.executionLogger(new Date(), "Login verification was successful");
            doValidationCheckPassword();
        } catch (WrongLoginException e) {
            Logger.errorLogger(new Date(), "Wrong login", e);
        }

    }

    public static void doValidationCheckPassword () {

        Logger.executionLogger(new Date(), "Check password");

        System.out.println("Enter the password");
        String password = SCANNER.nextLine();
        Matcher matcher = PATTERN.matcher(password);

        try {
            if (!matcher.find() || !password.equals(Encryption.deCoding(INFORMATION.getLogin()))) {
                for (int i = 2; i >= 0; i--) {
                    if (i == 0) {
                        throw new WrongPasswordException(15, "Wrong password");
                    }
                    Logger.executionLogger(new Date(), "Wrong password \n" + "Attempts left " + i);
                    System.out.println("Wrong password \n" + "Attempts left " + i);
                }
            }
            Logger.executionLogger(new Date(), "Password verification was successful");
        } catch (WrongPasswordException e) {
            Logger.errorLogger(new Date(), "Wrong Password", e);
        }

        PathCheck.pathFileCheck();

    }

}

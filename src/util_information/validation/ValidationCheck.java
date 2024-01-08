package util_information.validation;

import exception.WrongLoginException;
import exception.WrongPasswordException;
import logger.Logger;
import session_description.information.Information;
import session_description.session_time.Session;
import util_information.encryption.Encryption;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {

    static Scanner SCANNER = new Scanner(System.in);
    static Pattern PATTERN = Pattern.compile("^([0-9a-zA-Z_]{6,14})$");
    static Information INFORMATION = new Information();

    public static Session doValidationCheck() {

        Logger.executionLogger(new Date(), "Check login");

        try {
            for (int i = 2; i >= 0; i--) {

                System.out.println("Enter the login");
                String login = SCANNER.nextLine();
                Matcher matcher = PATTERN.matcher(login);

                if (!matcher.find() || !login.equals(Encryption.deCoding(INFORMATION.getLogin()))) {
                    if (i == 0) {
                        throw new WrongLoginException("Wrong login");
                    }
                    Logger.executionLogger(new Date(), "Wrong login \n" + "Attempts left " + i);
                    System.out.println("Wrong login \n" + "Attempts left " + i);
                } else {
                    break;
                }

            }
            Logger.executionLogger(new Date(), "Login verification was successful");

        } catch (WrongLoginException e) {
            Logger.errorLogger(new Date(), "Wrong login", e);
        }

        Logger.executionLogger(new Date(), "Check password");

        try {
            for (int i = 2; i >= 0; i--) {

                System.out.println("Enter the password");
                String password = SCANNER.nextLine();
                Matcher matcher = PATTERN.matcher(password);

                if (!matcher.find() || !password.equals(Encryption.deCoding(INFORMATION.getPassword()))) {
                    if (i == 0) {
                        throw new WrongPasswordException("Wrong password");
                    }
                    Logger.executionLogger(new Date(), "Wrong password attempts left " + i);
                    System.out.println("Wrong password \n" + "Attempts left " + i);
                } else {
                    break;
                }

            }
            Logger.executionLogger(new Date(), "Password verification was successful");
        } catch (WrongPasswordException e) {
            Logger.errorLogger(new Date(), "Wrong Password", e);
        }

        return new Session();

    }

}

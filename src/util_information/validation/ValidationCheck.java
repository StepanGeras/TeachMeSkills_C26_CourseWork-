package util_information.validation;

import exception.WrongLoginException;
import exception.WrongPasswordException;
import session_description.information.Information;
import util_information.encryption.Encryption;
import util_information.path_check.PathCheck;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationCheck {

    static Scanner SCANNER = new Scanner(System.in);
    static Pattern PATTERN = Pattern.compile("^([0-9a-zA-Z_]{6,14})$");
    static Information INFORMATION = new Information();

    public static void doValidationCheckLogin() {

        System.out.println("Enter the login");
        String login = SCANNER.nextLine();
        Matcher matcher = PATTERN.matcher(login);

        try {
            if (!matcher.find() || !login.equals(Encryption.deCoding(INFORMATION.getLogin()))) {
                throw new WrongLoginException(14, "Wrong login");
            }
            doValidationCheckPassword();
        } catch (WrongLoginException e) {
            ValidationCheck.doValidationCheckLogin();
        }



    }

    public static void doValidationCheckPassword () {

        System.out.println("Enter the password");
        String password = SCANNER.nextLine();
        Matcher matcher = PATTERN.matcher(password);

        try {
            if (!matcher.find() && !password.equals(Encryption.deCoding(INFORMATION.getPassword()))) {
                throw new WrongPasswordException(15, "Wrong password");
            }
        } catch (WrongPasswordException e) {
            ValidationCheck.doValidationCheckPassword();
        }

        PathCheck.pathFileCheck();

    }

}

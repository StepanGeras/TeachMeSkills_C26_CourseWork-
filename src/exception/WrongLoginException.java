package exception;

public class WrongLoginException extends Exception {

    public WrongLoginException(int validationExceptionCode, String login) {
        super(login);
    }

}

package exception;

public class WrongPasswordException extends Exception {

    public WrongPasswordException(int validationExceptionCode, String password) {
        super(password);
    }

}

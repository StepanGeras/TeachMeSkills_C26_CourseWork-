package exception;

public class WrongPasswordException extends Exception {

    private int validationExceptionCode;

    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(int validationExceptionCode, String password) {
        super(password);
        this.validationExceptionCode = validationExceptionCode;
    }

}

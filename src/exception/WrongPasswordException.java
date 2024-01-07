package exception;

public class WrongPasswordException extends Exception {

    public WrongPasswordException( String password) {
        super(password);
    }

}

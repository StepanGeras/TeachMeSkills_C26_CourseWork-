package exception;

public class WrongPathException extends Exception {

    public WrongPathException(int exceptionCode, String error) {
        super(error);
    }

}

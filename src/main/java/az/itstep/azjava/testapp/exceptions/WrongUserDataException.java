package az.itstep.azjava.testapp.exceptions;

public class WrongUserDataException extends RuntimeException {
    public WrongUserDataException(String message) {
        super(message);
    }
}

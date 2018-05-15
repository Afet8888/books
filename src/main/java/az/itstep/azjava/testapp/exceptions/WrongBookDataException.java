package az.itstep.azjava.testapp.exceptions;

public class WrongBookDataException extends RuntimeException {
    public WrongBookDataException(String message) {
        super(message);
    }
}

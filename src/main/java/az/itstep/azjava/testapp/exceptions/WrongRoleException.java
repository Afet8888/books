package az.itstep.azjava.testapp.exceptions;

public class WrongRoleException extends RuntimeException {
    public WrongRoleException(String message) {
        super(message);
    }
}

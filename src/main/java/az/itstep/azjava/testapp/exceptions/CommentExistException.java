package az.itstep.azjava.testapp.exceptions;

public class CommentExistException extends RuntimeException{
    public CommentExistException(String message) {
        super(message);
    }
}

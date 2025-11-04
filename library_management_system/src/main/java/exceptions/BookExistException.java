package exceptions;

public class BookExistException extends RuntimeException{
    public BookExistException(String s) {
        super(s);
    }
}

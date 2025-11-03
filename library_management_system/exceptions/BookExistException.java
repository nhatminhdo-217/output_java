package library_management_system.exceptions;

public class BookExistException extends RuntimeException{
    public BookExistException(String s) {
        super(s);
    }
}

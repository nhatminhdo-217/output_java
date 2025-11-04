package library_management_system.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException(String s) {
        super(s);
    }
}

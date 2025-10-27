package data_structure;

public class StudentException extends RuntimeException{

    public StudentException(String str) {
        super(str);
    }
}

class WrongIdFormatException extends StudentException {

    public WrongIdFormatException (String str) {
        super(str);
    }
}

class NonExistStudentException extends StudentException {

    public NonExistStudentException(String str) {
        super(str);
    }
    
}

class NonExistGenderException extends StudentException {

    public NonExistGenderException(String str) {
        super(str);
    }
    
}

class WrongGpaFormatException extends StudentException{

    public WrongGpaFormatException(String str) {
        super(str);
    }
}
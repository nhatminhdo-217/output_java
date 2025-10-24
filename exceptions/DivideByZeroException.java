package exceptions;

//Checked Exception
// public class DivideByZeroException extends Exception{
//     public DivideByZeroException(String s){
//         super(s);
//     }
// }

//Unchecked Exception
public class DivideByZeroException extends RuntimeException{
    public DivideByZeroException(String s){
        super(s);
    }
}
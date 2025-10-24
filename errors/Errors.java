package errors;

public class Errors {
    //int a = "abc"; //Compile time Errors cannot convert int to string
    //int b = 2 //miss semicolon

    int x = 5;
    int y = 0;
    int result = x/y;

    String str1 = "Hello";
    String str2 = "World";

    public static void main(String[] args) {
        Errors err = new Errors();
        // System.out.println(err.a);
        System.out.println(err.result); //runtime error: devide by zero

        //Logic error
        System.out.println(err.str1 + err.str2); //Expect: "Hello World" - Actual: "HelloWorld" miss: + " " +
    }
}

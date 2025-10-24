package exceptions;

import java.util.Scanner;

public class Exceptions {

    public double divide(int dividend, int divisor) {
        if (isDivideByZero(divisor)) {
            throw new DivideByZeroException("Cannot divide by 0. Please enter again");
        }
        return (double) dividend / divisor;
    }

    private boolean isDivideByZero(int divisor) {
        return divisor == 0 ? true : false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Exceptions exceptions = new Exceptions();

        while (true) {
            try {
                System.out.print("Enter diviend value: ");
                int diviend = sc.nextInt();

                System.out.print("Enter divisor value: ");
                int divisor = sc.nextInt();

                System.out.println("Value: " + exceptions.divide(diviend, divisor));
                break;
            } catch (DivideByZeroException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
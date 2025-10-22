package operator;

import java.util.Scanner;

public class Operator {

    Scanner sc = new Scanner(System.in);

    void menu() {
        System.out.println("\n========== Operator Menu ==========");
        System.out.println("1. Arithmetic Operator");
        System.out.println("2. Assignment Operator");
        System.out.println("3. Comparison Operator");
        System.out.println("4. Logic Operator");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    void arithmeticOperator() {
        System.out.println("\n--- Arithmetic Operators ---");
        System.out.println("1. +");
        System.out.println("2. -");
        System.out.println("3. *");
        System.out.println("4. /");
        System.out.println("5. %");
        System.out.println("6. ++");
        System.out.println("7. --");
        System.out.print("Choose an operator: ");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number (if needed): ");
        int b = sc.nextInt();

        switch (choice) {
            case 1 -> System.out.println("Result: " + (a + b));
            case 2 -> System.out.println("Result: " + (a - b));
            case 3 -> System.out.println("Result: " + (a * b));
            case 4 -> System.out.println("Result: " + (a / b));
            case 5 -> System.out.println("Result: " + (a % b));
            case 6 -> {
                a++;
                System.out.println("After increment: " + a);
            }
            case 7 -> {
                a--;
                System.out.println("After decrement: " + a);
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    void assignmentOperator() {
        System.out.println("\n--- Assignment Operators ---");
        System.out.println("1. +=");
        System.out.println("2. -=");
        System.out.println("3. *=");
        System.out.println("4. /=");
        System.out.println("5. %=");
        System.out.println("6. &=");
        System.out.println("7. |=");
        System.out.println("8. ^=");
        System.out.println("9. >>=");
        System.out.println("10. <<=");
        System.out.print("Choose an operator: ");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        switch (choice) {
            case 1 -> { a += b; System.out.println("Result: " + a); }
            case 2 -> { a -= b; System.out.println("Result: " + a); }
            case 3 -> { a *= b; System.out.println("Result: " + a); }
            case 4 -> { a /= b; System.out.println("Result: " + a); }
            case 5 -> { a %= b; System.out.println("Result: " + a); }
            case 6 -> { a &= b; System.out.println("Result: " + a); }
            case 7 -> { a |= b; System.out.println("Result: " + a); }
            case 8 -> { a ^= b; System.out.println("Result: " + a); }
            case 9 -> { a >>= b; System.out.println("Result: " + a); }
            case 10 -> { a <<= b; System.out.println("Result: " + a); }
            default -> System.out.println("Invalid choice!");
        }
    }

    void comparisionOperator() {
        System.out.println("\n--- Comparison Operators ---");
        System.out.println("1. ==");
        System.out.println("2. !=");
        System.out.println("3. >");
        System.out.println("4. <");
        System.out.println("5. >=");
        System.out.println("6. <=");
        System.out.print("Choose an operator: ");
        int choice = sc.nextInt();

        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        boolean result = switch (choice) {
            case 1 -> a == b;
            case 2 -> a != b;
            case 3 -> a > b;
            case 4 -> a < b;
            case 5 -> a >= b;
            case 6 -> a <= b;
            default -> false;
        };

        System.out.println("Result: " + result);
    }

    void logicOperator() {
        System.out.println("\n--- Logic Operators ---");
        System.out.println("1. &&");
        System.out.println("2. ||");
        System.out.println("3. !");
        System.out.print("Choose an operator: ");
        int choice = sc.nextInt();

        boolean a, b;

        switch (choice) {
            case 1, 2 -> {
                System.out.print("Enter first boolean (true/false): ");
                a = sc.nextBoolean();
                System.out.print("Enter second boolean (true/false): ");
                b = sc.nextBoolean();

                if (choice == 1)
                    System.out.println("Result: " + (a && b));
                else
                    System.out.println("Result: " + (a || b));
            }
            case 3 -> {
                System.out.print("Enter a boolean (true/false): ");
                a = sc.nextBoolean();
                System.out.println("Result: " + (!a));
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    void run() {
        while (true) {
            menu();
            int choose = sc.nextInt();

            switch (choose) {
                case 1 -> arithmeticOperator();
                case 2 -> assignmentOperator();
                case 3 -> comparisionOperator();
                case 4 -> logicOperator();
                case 5 -> {
                    System.out.println("Exiting program... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        Operator op = new Operator();
        op.run();
    }
}
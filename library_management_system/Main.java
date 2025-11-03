package library_management_system;

import java.util.Scanner;

import library_management_system.book.BookProgram;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Book Management");
                System.out.println("2. User Management");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(SCANNER.nextLine());
                program(choice);

            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void program(int choice) {
        switch (choice) {
            case 1:
                BookProgram bookProgram = new BookProgram();
                bookProgram.run();
                break;

            case 2:
                System.out.println("This is case 2");
                break;

            case 0:
                System.out.println("Exit...");
                System.exit(0);
                break;

            default:
                System.err.println("Invalid input. Please enter 1, 2 or 0!");
                break;
        }
    }
}
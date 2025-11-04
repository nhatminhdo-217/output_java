package com.example;
import java.util.Scanner;

import com.example.book.BookProgram;
import com.example.user.UserProgram;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);
    private BookProgram bookProgram;
    private UserProgram userProgram;

    public Main() {
        this.bookProgram = new BookProgram();
        this.userProgram = new UserProgram();
    }

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
                bookProgram.run();
                break;

            case 2:
                userProgram.run();
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
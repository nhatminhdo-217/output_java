package library_management_system.book;

import java.util.Scanner;

public class BookProgram {
    private static final Scanner SC = new Scanner(System.in);
    private 

    public void run() {
        while (true) {
            try {
                System.out.println("========== Book Management ==========");
                System.out.println("1. Add Book");
                System.out.println("2. Update Book");
                System.out.println("3. Delete Book");
                System.out.println("4. Search Book");
                System.out.println("5. List All Book");
                System.out.println("0. Back");

                System.out.print("Choice: ");
                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        Book book = addBookProgram();
                        
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 0:

                        break;

                    default:
                        System.err.println("Invalid input. Please enter number from 0 - 5!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            }
        }
    }
}

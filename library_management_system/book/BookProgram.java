package library_management_system.book;

import java.util.Scanner;

import library_management_system.Validate;

public class BookProgram {
    private static final Scanner SC = new Scanner(System.in);
    private BookList listBooks = new BookList();

    public void run() {
        while (true) {
            try {
                System.out.println("\n========== Book Management ==========");
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
                        Book addBook = listBooks.addBookProgram();
                        listBooks.addBook(addBook);
                        System.out.println("Add successfully");
                        break;
                    case 2:
                        System.out.print("Book ID to update: ");
                        String updateId = Validate.validateIdInput(SC.nextLine());
                        listBooks.changeInformationById(updateId);
                        System.out.println("Update successfully");
                        break;
                    case 3:
                        System.out.print("Book ID to delete: ");
                        String deleteId = Validate.validateIdInput(SC.nextLine());
                        Book deleteBook = listBooks.getBookById(deleteId);
                        listBooks.deleteBook(deleteBook);
                        System.out.println("Delete successfully");
                        break;
                    case 4:
                        listBooks.searchBook();
                        break;
                    case 5:
                        listBooks.listAllBook();
                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter number from 0 - 5!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

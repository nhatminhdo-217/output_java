package com.example.borrow_records;

import java.util.List;
import java.util.Scanner;

import com.example.exceptions.BookExistException;
import com.example.exceptions.UserExistException;
import com.example.validate.Validate;

public class BorrowProgram {
    private static final Scanner SC = new Scanner(System.in);
    private RecordList listRecords = new RecordList();


    public void run() {
        while (true) {
            try {
                System.out.println("\n========== Borrow Management ==========");
                System.out.println("1. Borrow Book");
                System.out.println("2. Return Book");
                System.out.println("3. View History");
                System.out.println("0. Save and back");

                System.out.print("Choice: ");
                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("User ID: ");
                        String userBorrowId = Validate.validateUserIdInput(SC.nextLine());
                        System.out.print("Book ID: ");
                        String bookBorrowId = Validate.validateIdInput(SC.nextLine());
                        listRecords.borrowBook(userBorrowId, bookBorrowId);
                        System.out.println("User " + userBorrowId + " borrow a book " + bookBorrowId);
                        break;
                    case 2:
                        System.out.print("User ID: ");
                        String userReturnId = Validate.validateUserIdInput(SC.nextLine());
                        System.out.print("Book ID: ");
                        String bookReturnId = Validate.validateIdInput(SC.nextLine());
                        listRecords.returnBook(userReturnId, bookReturnId);
                        System.out.println("User " + userReturnId + " return a book " + bookReturnId);
                        break;
                    case 3:
                        System.out.print("User ID to view history: ");
                        String userId = Validate.validateUserIdInput(SC.nextLine());
                        List<Record> userRecords = listRecords.getUserRecordList(userId);
                        userRecords.stream().forEach(System.out::println);
                        break;
                    case 0:
                        listRecords.exportToCSV();
                        listRecords.exportToBookCSV();
                        System.out.println("Saved to record.csv, Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter number from 0 - 3!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (UserExistException uee) {
                System.err.println(uee.getMessage());
            } catch (BookExistException bee) {
                System.err.println(bee.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

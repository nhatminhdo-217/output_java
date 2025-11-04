package com.example.user;

import java.util.Scanner;

import com.example.exceptions.UserExistException;
import com.example.validate.Validate;

public class UserProgram {
    private static final Scanner SC = new Scanner(System.in);
    private UserList listUsers = new UserList();

    public void run() {
        while (true) {
            try {
                System.out.println("\n========== User Management ==========");
                System.out.println("1. Add User");
                System.out.println("2. Update User");
                System.out.println("3. Delete User");
                System.out.println("4. Search User");
                System.out.println("5. List All User");
                System.out.println("0. Back");

                System.out.print("Choice: ");
                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        User addUser = listUsers.addUserProgram();
                        listUsers.addUser(addUser);
                        System.out.println("Add successfully");
                        break;
                    case 2:
                        System.out.print("User ID to update(Uxxx): ");
                        String updateId = Validate.validateUserIdInput(SC.nextLine());
                        listUsers.changeInformationById(updateId);
                        break;
                    case 3:
                        System.out.print("User ID to delete(Uxxx): ");
                        String deleteId = Validate.validateUserIdInput(SC.nextLine());
                        User deleteUser = listUsers.getUserById(deleteId);

                        System.out.println("Do you really want to delete this user? (y/n)");
                        System.out.println(deleteUser);
                        String confirm = SC.nextLine();
                        if (Validate.validateYesNo(confirm)) {
                            listUsers.deleteUser(deleteUser);
                            System.out.println("Delete successfully");
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                        break;
                    case 4:
                        listUsers.searchUser();
                        break;
                    case 5:
                        listUsers.listAllUser();
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
            } catch (UserExistException uee) {
                System.err.println(uee.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

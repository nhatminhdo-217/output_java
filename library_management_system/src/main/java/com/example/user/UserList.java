package com.example.user;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.exceptions.NoContextInputException;
import com.example.exceptions.UserExistException;
import com.example.validate.Validate;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class UserList {
    private List<User> listUsers;
    private static final Scanner SC = new Scanner(System.in);
    private static final String USER_CSV_PATH = "library_management_system/data/user.csv";

    public UserList() {
        this.listUsers = new ArrayList<>();
        loadUserFromCSV(USER_CSV_PATH);

        User.initializeIdCounter(listUsers);
    }

    private void loadUserFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                try {
                    String id = row[0];
                    String name = row[1];
                    String email = row[2];
                    String phone = Validate.validateImportPhone(row[3]);
                    
                    User user = new User(id, name, email, phone); 
                    this.listUsers.add(user);
                } catch (IllegalArgumentException iae) {
                     System.err.println("Error while load User.csv (row " + (i + 1) + "): " + iae.getMessage());
                } catch (Exception e) {
                    System.err.println("Error data from User.csv (row " + (i + 1) + ")");
                }
            }
        } catch (IOException | CsvException e) {
            System.err.println("Can not load " + filePath + ".");
        }
    }

    public User addUserProgram() {
        while (true) {
            try {
                System.out.println("========== Add User ==========");
                System.out.print("Name: ");
                String name = Validate.validateInput(SC.nextLine());

                System.out.print("Email (demo@example.com): ");
                String email = Validate.validateInput(SC.nextLine());
                if (!email.matches(User.getEmailPattern())) {
                    throw new IllegalArgumentException("Email pattern is invalid. Try again.");
                }

                System.out.print("Phone (0912345678): ");
                String phone = Validate.validateInput(SC.nextLine());
                if (!phone.matches(User.getPhonePattern())) {
                    throw new IllegalArgumentException("Phone pattern is invalid. Try again.");
                }

                User.initializeIdCounter(listUsers);
                User newUser = new User(name, email, phone);

                if (isEmailExist(email)) {
                    throw new IllegalArgumentException("This email is already exist");
                }

                if (isPhoneExist(phone)) {
                    throw new IllegalArgumentException("This phone number is already exist");
                }

                return newUser;

            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a number");
            } catch (NoContextInputException ncie) {
                System.err.println(ncie.getMessage());
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            }
        }
    }

    private boolean isPhoneExist(String phone) {
        return listUsers.stream().anyMatch(u -> u.getPhone().equals(phone));
    }

    private boolean isEmailExist(String email) {
        return listUsers.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public void addUser(User addUser) {
        if (!isUserExist(addUser)) {
            listUsers.add(addUser);
        }else {
            throw new UserExistException("This user is already exist with ID: " + addUser.getId());
        }
    }

    private boolean isUserExist(User addUser) {
        return listUsers.stream().anyMatch(b -> b.getId().equals(addUser.getId()));
    }

    public void changeInformationById(String updateId) {
        if (isUserExistById(updateId)) {
            User user = getUserById(updateId);
            optionChangeInformation(user);
        }else {
            throw new UserExistException("User " + updateId + " do not exist.");
        }
    }

    private void optionChangeInformation(User user) {
        while (true) {
            try {
                System.out.println("\n What do you want to change: ");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Phone");
                System.out.println("0. Back");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        changeUserName(user);
                        System.out.println("Change name successfully!");
                        break;
                    case 2:
                        changeUserEmail(user);
                        System.out.println("Change email successfully!");
                        break;
                    case 3:
                        changeUserPhone(user);
                        System.out.println("Change phone successfully!");
                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter from 0 - 3!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a number!");
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void changeUserPhone(User user) throws Exception {
        System.out.println("Phone: " + user.getPhone());
        System.out.print("Change: ");
        String newPhone = Validate.validateInput(SC.nextLine());
        if (newPhone.equals(user.getPhone())) {
            throw new Exception("New phone is not different from old phone.");
        }
        if (isPhoneExist(newPhone)) {
             throw new IllegalArgumentException("Phone number already exists.");
        }
        user.setPhone(newPhone); // Phương thức setPhone đã có validate pattern
    }

    private void changeUserEmail(User user) throws Exception {
        System.out.println("Email: " + user.getEmail());
        System.out.print("Change: ");
        String newEmail = Validate.validateInput(SC.nextLine());
        if (newEmail.equals(user.getEmail())) {
            throw new Exception("New email is not different from old email.");
        }
        if (isEmailExist(newEmail)) {
             throw new IllegalArgumentException("Email already exists.");
        }
        user.setEmail(newEmail); 
    }

    private void changeUserName(User user) throws Exception {
        System.out.println("Name: " + user.getName());
        System.out.print("Change: ");
        String newName = Validate.validateInput(SC.nextLine());
        if (newName.equals(user.getName())) {
            throw new Exception("New name is not different from old name.");
        }
        user.setName(newName);
    }

    public boolean isUserExistById(String updateId) {
        return listUsers.stream().anyMatch(u -> u.getId().equals(updateId));
    }

    public User getUserById(String id) {
        if (isUserExistById(id)) {
            return listUsers.stream().filter(b -> b.getId().equals(id)).findFirst().get();
        }

        throw new UserExistException("User " + id + " do not exist.");
    }

    public void deleteUser(User deleteUser) {
        if (isUserExist(deleteUser)) {
            listUsers.remove(deleteUser);
        } else {
            throw new UserExistException("This user with id: " + deleteUser.getId() + " do not exist.");
        }
    }

    public void searchUser() {
         while (true) {
            try {
                System.out.println("\nSearch user by: ");
                System.out.println("1. Name");
                System.out.println("2. Email");
                System.out.println("3. Phone");
                System.out.println("0. Back");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(SC.nextLine());
                boolean found = false;

                switch (choice) {
                    case 1:
                        found = searchUserByName();
                        break;
                    case 2:
                        found = searchUserByEmail();
                        break;
                    case 3:
                        found = searchUserByPhone();
                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter from 0 - 3.");
                        break;
                }
                
                if (!found && choice != 0) {
                    System.out.println("No user found.");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid input. Please enter a number");
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private boolean searchUserByName() throws IllegalArgumentException {
        System.out.print("Search: ");
        String searchValue = Validate.validateInput(SC.nextLine());
        List<User> results = listUsers.stream()
            .filter(u -> u.getName().toLowerCase().contains(searchValue.toLowerCase()))
            .toList();
        results.forEach(System.out::println);
        return !results.isEmpty();
    }

    private boolean searchUserByEmail() throws IllegalArgumentException {
        System.out.print("Search: ");
        String searchValue = Validate.validateInput(SC.nextLine());
        List<User> results = listUsers.stream()
            .filter(u -> u.getEmail().toLowerCase().contains(searchValue.toLowerCase()))
            .toList();
        results.forEach(System.out::println);
        return !results.isEmpty();
    }

    private boolean searchUserByPhone() throws IllegalArgumentException {
        System.out.print("Search: ");
        String searchValue = Validate.validateInput(SC.nextLine());
        List<User> results = listUsers.stream()
            .filter(u -> u.getPhone().contains(searchValue))
            .toList();
        results.forEach(System.out::println);
        return !results.isEmpty();
    }

    public void listAllUser() {
        if (listUsers.isEmpty()) {
            System.out.println("No User in the system");
            return;
        }

        listUsers.stream().forEach(System.out::println);
    }

    public void exportToCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(USER_CSV_PATH))) {
            String[] header = {"id", "name", "email", "phone"};
            writer.writeNext(header);
            for (User user : listUsers) {
                String[] row = new String[4];
                row[0] = user.getId();
                row[1] = user.getName();
                row[2] = user.getEmail();
                row[3] = Validate.validateExportPhone(user.getPhone());

                writer.writeNext(row);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } 
    }
}
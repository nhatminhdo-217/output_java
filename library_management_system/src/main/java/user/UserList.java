package user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.NoContextInputException;
import exceptions.UserExistException;
import validate.Validate;

public class UserList {
    private List<User> listUsers;
    private static final Scanner SC = new Scanner(System.in);

    public UserList() {
        this.listUsers = new ArrayList<>();
        listUsers.add(new User("Alice Nguyen", "alice.nguyen@example.com", "0912345678"));
        listUsers.add(new User("Bob Tran", "bob.tran@example.com", "0901234567"));
        listUsers.add(new User("Charlie Le", "charlie.le@example.com", "0987654321"));
        listUsers.add(new User("Daisy Pham", "daisy.pham@example.com", "0971234567"));
        listUsers.add(new User("Ethan Vo", "ethan.vo@example.com", "0934567890"));
        listUsers.add(new User("Fiona Ho", "fiona.ho@example.com", "0949876543"));
        listUsers.add(new User("George Dang", "george.dang@example.com", "0923456789"));
        listUsers.add(new User("Hannah Bui", "hannah.bui@example.com", "0956789012"));
        listUsers.add(new User("Ian Do", "ian.do@example.com", "0961237890"));
        listUsers.add(new User("Jenny Ly", "jenny.ly@example.com", "0994561230"));

        User.initializeIdCounter(listUsers);
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

    private boolean isUserExistById(String updateId) {
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
}
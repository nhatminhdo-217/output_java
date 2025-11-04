package book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.BookExistException;
import exceptions.NoContextInputException;
import validate.Validate;

public class BookList {
    private List<Book> listBooks;
    private static final Scanner SC = new Scanner(System.in);

    public BookList() {
        this.listBooks = new ArrayList<>();

        listBooks.add(new Book("Clean Code", "Robert C. Martin", 2008, 15.99, true));
        listBooks.add(new Book("Effective Java", "Joshua Bloch", 2018, 18.50, true));
        listBooks.add(new Book("Head First Java", "Kathy Sierra", 2005, 13.75, true));
        listBooks.add(new Book("Java Concurrency in Practice", "Brian Goetz", 2006, 17.80, true));
        listBooks.add(new Book("Spring in Action", "Craig Walls", 2016, 14.25, true));
        listBooks.add(new Book("Thinking in Java", "Bruce Eckel", 2006, 16.40, false));
        listBooks.add(new Book("Core Java Volume I", "Cay S. Horstmann", 2015, 19.20, true));
        listBooks.add(new Book("Java: The Complete Reference", "Herbert Schildt", 2021, 22.10, true));
        listBooks.add(new Book("Beginning Programming with Java", "Barry Burd", 2020, 11.90, false));
        listBooks.add(new Book("Java Design Patterns", "James W. Cooper", 2017, 13.50, true));
    }

    // Add Book
    public void addBook(Book book) {
        if (!isBookExist(book)) {
            listBooks.add(book);
        } else {
            throw new BookExistException("This book is already exist with ID: " + book.getId());
        }
    }

    // Change Book infomation
    public void changeInformationById(String id) {
        if (isBookExistById(id)) {
            Book book = getBookById(id);
            optionChangeInformation(book);
        } else {
            throw new BookExistException("This book with ID: " + id + " do not exist.");
        }
    }

    // Delete Book
    public void deleteBook(Book book) {
        if (isBookExist(book)) {
            listBooks.remove(book);
        } else {
            throw new BookExistException("This book with id: " + book.getId() + " do not exist.");
        }
    }

    // Search Book by title, author or year
    public void searchBook() {
        while (true) {
            try {
                System.out.println("\nSearch book by: ");
                System.out.println("1. Title");
                System.out.println("2. Author");
                System.out.println("3. Year");
                System.out.println("0. Back");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        searchBookByTitle();
                        break;
                    case 2:
                        searchBookByAuthor();
                        break;
                    case 3:
                        searchBookByYear();
                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter from 0 - 3.");
                        break;
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

    // List all book
    public void listAllBook() {
        listBooks.stream().forEach(System.out::println);
    }

    public Book addBookProgram() {
        while (true) {
            try {
                System.out.println("========== Add Book ==========");
                System.out.print("Title: ");
                String title = Validate.validateInput(SC.nextLine());
                System.out.print("Author: ");
                String author = Validate.validateInput(SC.nextLine());
                System.out.print("Year: ");
                int year = Validate.validateIntegerInput(SC.nextLine());
                System.out.print("Price: ");
                double price = Validate.validateIntegerInput(SC.nextLine());
                System.out.print("Available (default is true): ");
                boolean available = Validate.validateAvailableInput(SC.nextLine());

                Book.initializeIdCounter(listBooks);
                return new Book(title, author, year, price, available);

            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a number");
            } catch (NoContextInputException ncie) {
                System.err.println(ncie.getMessage());
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
            }
        }
    }

    private void searchBookByYear() throws NumberFormatException, IllegalArgumentException {
        System.out.print("Search: ");
        int searchValue = Integer.parseInt(SC.nextLine().trim());

        if (searchValue <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }

        listBooks.stream().filter(b -> b.getYear() == searchValue).forEach(System.out::println);

    }

    private void searchBookByAuthor() throws IllegalArgumentException {
        System.out.print("Search: ");
        String searchValue = SC.nextLine().trim();

        if (searchValue.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null.");
        }

        listBooks.stream().filter(b -> b.getAuthor().toLowerCase().contains(searchValue.toLowerCase())).forEach(System.out::println);
    }

    private void searchBookByTitle() throws IllegalArgumentException {
        System.out.print("Search: ");
        String searchValue = SC.nextLine().trim();

        if (searchValue.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null.");
        }

        listBooks.stream().filter(b -> b.getTitle().toLowerCase().contains(searchValue.toLowerCase())).forEach(System.out::println);
    }

    private boolean isBookExist(Book book) {
        return listBooks.stream().anyMatch(b -> b.getId().equals(book.getId()));
    }

    private boolean isBookExistById(String id) {
        return listBooks.stream().anyMatch(b -> b.getId().equals(id));
    }

    public Book getBookById(String id) {
        return listBooks.stream().filter(b -> b.getId().equals(id)).findFirst().get();
    }

    private void optionChangeInformation(Book book) {
        while (true) {
            try {
                System.out.println("\n What do you want to change: ");
                System.out.println("1. Title");
                System.out.println("2. Author");
                System.out.println("3. Year");
                System.out.println("4. Price");
                System.out.println("5. Available");
                System.out.println("0. Back");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(SC.nextLine());

                switch (choice) {
                    case 1:
                        changeBookTitle(book);
                        System.out.println("Change title successfully!");
                        break;
                    case 2:
                        changeBookAuthor(book);
                        System.out.println("Change author successfully!");
                        break;
                    case 3:
                        changeBookYear(book);
                        System.out.println("Change year successfully!");
                        break;
                    case 4:
                        changeBookPrice(book);
                        System.out.println("Change price successfully!");

                        break;
                    case 5:
                        changeBookAvailable(book);
                        System.out.println("Change status successfully!");

                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;

                    default:
                        System.err.println("Invalid input. Please enter from 0 - 5!");
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

    private void changeBookTitle(Book book) throws Exception {
        System.out.println("Title: " + book.getTitle());
        System.out.print("Change: ");
        String newTitle = SC.nextLine();
        if (newTitle.equals(book.getTitle().trim())) {
            throw new Exception("New title is not different from old title.");
        }
        book.setTitle(newTitle);

    }

    private void changeBookAuthor(Book book) throws Exception {
        System.out.println("Author: " + book.getAuthor());
        System.out.print("Change: ");
        String newAuthor = SC.nextLine().trim();
        if (newAuthor.equals(book.getAuthor())) {
            throw new Exception("New author is not different from old author.");
        }
        book.setAuthor(newAuthor);
    }

    private void changeBookYear(Book book) throws NumberFormatException, IllegalArgumentException, Exception {
        System.out.println("Year: " + book.getYear());
        System.out.print("Change: ");
        int newYear = Integer.parseInt(SC.nextLine().trim());
        if (newYear == book.getYear()) {
            throw new Exception("New year is not different from old year.");
        }
        book.setYear(newYear);
    }

    private void changeBookPrice(Book book) throws NumberFormatException, IllegalArgumentException, Exception {
        System.out.println("Price: " + book.getPrice());
        System.out.print("Change: ");
        double newPrice = Double.parseDouble(SC.nextLine().trim());
        if (newPrice == book.getPrice()) {
            throw new Exception("New price is not different from old price.");
        }
        book.setPrice(newPrice);
    }

    private void changeBookAvailable(Book book) throws NumberFormatException, IllegalArgumentException, Exception {
        System.out.println("Available: " + book.isAvailable());
        System.out.print("Change to " + !book.isAvailable() + ":(y/n) ");
        String newStatus = SC.nextLine().trim();
        if (Validate.validateYesNo(newStatus)) {
            book.setAvailable(!book.isAvailable());
        }
    }

}

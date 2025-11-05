package com.example.book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.exceptions.BookExistException;
import com.example.exceptions.NoContextInputException;
import com.example.validate.Validate;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class BookList {
    private List<Book> listBooks;
    private static final Scanner SC = new Scanner(System.in);
    private static final String BOOK_CSV_PATH = "library_management_system/data/book.csv";

    public BookList() {
        this.listBooks = new ArrayList<>();
        loadBookFromCSV(BOOK_CSV_PATH);
        Book.initializeIdCounter(listBooks);
    }

    private void loadBookFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);

                try {
                    String id = row[0];
                    String title = row[1];
                    String author = row[2];
                    int year = Integer.parseInt(row[3]);
                    double price = Double.parseDouble(row[4]);
                    boolean available = Boolean.parseBoolean(row[5]);

                    Book book = new Book(id, title, author, year, price, available);
                    this.listBooks.add(book);

                } catch (NumberFormatException nfe) {
                    System.err.println("Error at row: " + (i+1) + ". Error: " + nfe.getMessage());
                } catch (IllegalArgumentException iae) {
                    System.err.println("Error at row " + (i+1) + " with error: " + iae.getMessage());
                }
            }
        } catch (IOException | CsvException e) {
            System.err.println("Error while load file: " + e.getMessage());
        }
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

    public boolean isBookExistById(String id) {
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

    public void exportToCSV() {
                try (CSVWriter writer = new CSVWriter(new FileWriter(BOOK_CSV_PATH))) {
            String[] header = {"id", "title", "author", "year", "price", "available"};
            writer.writeNext(header);
            for (Book book : listBooks) {
                String[] row = new String[6];
                row[0] = book.getId();
                row[1] = book.getTitle();
                row[2] = book.getAuthor();
                row[3] = String.valueOf(book.getYear());
                row[4] = String.valueOf(book.getPrice());
                row[5] = String.valueOf(book.isAvailable());

                writer.writeNext(row);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } 
    }

    public boolean isBookAvailableById(String id) {
        Book book = listBooks.stream().filter(b -> b.getId().equalsIgnoreCase(id)).findFirst().get();

        return book.isAvailable();
    }
}

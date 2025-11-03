package library_management_system.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import library_management_system.Validate;
import library_management_system.exceptions.BookExistException;

public class BookList {
    private List<Book> listBooks;
    private static final Scanner SC = new Scanner(System.in);

    public BookList() {
        this.listBooks = new ArrayList<>();
    }

    //Add Book
    public void addBook(Book book) {
        if (!isBookExist(book)) {
            listBooks.add(book);
        } else {
            throw new BookExistException("This book is already exist with ID: " + book.getId());
        }
    }

    //Change Book infomation
    public void changeInformationById(String id) {
        if (isBookExistById(id)) {
            Book book = getBookById(id);
            optionChangeInformation(book);
        } else {
            throw new BookExistException("This book with ID: " + id + " do not exist.");
        }
    }

    //Delete Book
    public void deleteBook(Book book) {
        if (isBookExist(book)) {
            listBooks.remove(book);
        }else {
            throw new BookExistException("This book with id: " + book.getId() + " do not exist.");
        }
    }

    //Search Book by title, author or year
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
                        break;
                
                    default:
                        System.err.println("Invalid input. Please enter from 0 - 3.");
                        break;
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid input. Please enter a number");
            }
        }
    }

    //List all book
    public void listAllBook() {
        listBooks.stream().forEach(System.out::println);
    }

    public Book bookProgram(){
        while (true) {
            try {
                System.out.println("========== Add Book ==========");
                System.out.println("Title: ");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
    private void searchBookByYear() {
        try {
            System.out.print("Search: ");
            int searchValue = Integer.parseInt(SC.nextLine());

            if (searchValue <= 0) {
                throw new IllegalArgumentException("Year must be greater than 0");
            }

            listBooks.stream().filter(b -> b.getYear() == searchValue).forEach(System.out::println);;

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid input. Please enter a number");
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        }
    }

    private void searchBookByAuthor() {
        try {
            System.out.println("Search: ");
            String searchValue = SC.nextLine();

            if (searchValue.isEmpty()) {
                throw new IllegalArgumentException("Author cannot be null.");
            }

            listBooks.stream().filter(b -> b.getAuthor().contains(searchValue)).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void searchBookByTitle() {
        try {
            System.out.println("Search: ");
            String searchValue = SC.nextLine();

            if (searchValue.isEmpty()) {
                throw new IllegalArgumentException("Title cannot be null.");
            }

            listBooks.stream().filter(b -> b.getTitle().contains(searchValue)).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isBookExist(Book book) {
        return listBooks.stream().anyMatch(b -> b.getId().equals(book.getId()));
    }

    private boolean isBookExistById(String id) {
        return listBooks.stream().anyMatch(b -> b.getId().equals(id));
    }

    private Book getBookById(String id) {
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
                        break;

                    default:
                        System.err.println("Invalid input. Please enter from 0 - 5!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a number!");
            }
        }
    }

    private void changeBookTitle(Book book) {
        try {
            System.out.println("Title: " + book.getTitle());
            System.out.print("Change: ");
            String newTitle = SC.nextLine();
            if (newTitle.equals(book.getTitle())) {
                throw new Exception("New title is not different from old title.");
            }
            book.setTitle(newTitle);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void changeBookAuthor(Book book) {
        try {
            System.out.println("Author: " + book.getAuthor());
            System.out.print("Change: ");
            String newAuthor = SC.nextLine();
            if (newAuthor.equals(book.getAuthor())) {
                throw new Exception("New author is not different from old author.");
            }
            book.setAuthor(newAuthor);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void changeBookYear(Book book) {
        try {
            System.out.println("Year: " + book.getYear());
            System.out.print("Change: ");
            int newYear = Integer.parseInt(SC.nextLine());
            if (newYear == book.getYear()) {
                throw new Exception("New year is not different from old year.");
            }
            book.setYear(newYear);

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid input. Please input a number.");
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void changeBookPrice(Book book) {
        try {
            System.out.println("Price: " + book.getPrice());
            System.out.print("Change: ");
            double newPrice = Double.parseDouble(SC.nextLine());
            if (newPrice == book.getPrice()) {
                throw new Exception("New price is not different from old price.");
            }
            book.setPrice(newPrice);

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid input. Please input a number.");
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void changeBookAvailable(Book book) {
        try {
            System.out.println("Available: " + book.isAvailable());
            System.out.print("Change to " + !book.isAvailable() + ":(y/n) ");
            String newStatus = SC.nextLine();
            if (Validate.validateYesNo(newStatus)) {
                book.setAvailable(!book.isAvailable());
            }

        } catch (NumberFormatException nfe) {
            System.err.println("Invalid input. Please input a number.");
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

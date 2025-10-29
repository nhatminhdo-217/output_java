package stream_api.book_library;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    List<Book> library = Arrays.asList(
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, LocalDate.of(1925, 12, 03), 10.99,
                    180),
            new Book(2, "1984", "George Orwell", Genre.FICTION, LocalDate.of(1949, 10, 12), 12.50, 328),
            new Book(3, "Sapiens", "Yuval Noah Harari", Genre.NON_FICTION, LocalDate.of(2011, 06, 03), 15.75, 512),
            new Book(4, "Dune", "Frank Herbert", Genre.SCIENCE_FICTION, LocalDate.of(1965, 04, 23), 14.99, 412),
            new Book(5, "A Brief History of Time", "Stephen Hawking", Genre.NON_FICTION, LocalDate.of(1988, 06, 22),
                    11.99, 256),
            new Book(6, "The Hobbit", "J.R.R. Tolkien", Genre.FANTASY, LocalDate.of(1937, 04, 25), 9.99, 310),
            new Book(7, "The Catcher in the Rye", "J.D. Salinger", Genre.FICTION, LocalDate.of(1951, 11, 23), 8.50,
                    224),
            new Book(8, "Foundation", "Isaac Asimov", Genre.SCIENCE_FICTION, LocalDate.of(1951, 01, 29), 13.00, 255));

    public static void main(String[] args) {
        Main main = new Main();

        //Find all book published after 1950
        // List<Book> after1950sBook = main.library.stream().filter(book -> book.getPublishDate().getYear() > 1950).collect(Collectors.toList());
        // System.out.println("---------- Stream Result ----------");
        // System.out.println("List book publish after 1950");
        // after1950sBook.forEach(System.out::println);

        //Get list of all book title
        main.library.stream().map(book -> book.getTitle()).forEach(System.out::println);;
    }
}

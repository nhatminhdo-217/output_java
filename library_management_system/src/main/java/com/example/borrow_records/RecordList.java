package com.example.borrow_records;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.book.Book;
import com.example.book.BookList;
import com.example.exceptions.BookExistException;
import com.example.exceptions.UserExistException;
import com.example.user.UserList;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class RecordList {
    private List<Record> listRecords;
    private UserList listUsers;
    private BookList listBooks;
    private static final String RECORD_CSV_PATH = "library_management_system/data/record.csv";

    public RecordList(UserList listUsers, BookList listBooks) {
        this.listRecords = new ArrayList<>();
        this.listUsers = listUsers;
        this.listBooks = listBooks;
        loadRecordsFromCSV(RECORD_CSV_PATH);
        Record.initializeIdCounter(listRecords);
    }

    private void loadRecordsFromCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                try {
                    String recordId = row[0];
                    String userId = row[1];
                    String bookId = row[2];
                    LocalDate borrowDate = LocalDate.parse(row[3], formatter);
                    LocalDate returnDate = null;
                    if (row.length > 4 && row[4] != null && !row[4].trim().isEmpty()) {
                        returnDate = LocalDate.parse(row[4].trim(), formatter);
                    }

                    Record record = new Record(recordId, userId, bookId, borrowDate, returnDate);
                    this.listRecords.add(record);
                } catch (DateTimeParseException e) {
                    System.err.println("Error while parse local date: " + e.getMessage());
                }
            }
        } catch (IOException | CsvException e) {
            System.err.println("Fatal error: " + e.getMessage());
        } catch (IllegalArgumentException iae) {
            System.err.println("Fatal error: " + iae.getMessage());
        }
    }

    public void borrowBook(String userBorrowId, String bookBorrowId) {
        if (!listUsers.isUserExistById(userBorrowId)) {
            throw new UserExistException("User with id " + userBorrowId + " do not exist.");
        }

        if (!listBooks.isBookExistById(bookBorrowId)) {
            throw new BookExistException("Book with id " + bookBorrowId + " do not exist.");
        }

        if (!listBooks.isBookAvailableById(bookBorrowId)) {
            throw new BookExistException("Book with id " + bookBorrowId + " is not available.");
        }

        Record record = new Record(userBorrowId, bookBorrowId, LocalDate.now(), null);
        Book book = listBooks.getBookById(bookBorrowId);
        book.setAvailable(false);
        this.listRecords.add(record);
    }

    public void returnBook(String userReturnId, String bookReturnId) {
        if (!listUsers.isUserExistById(userReturnId)) {
            throw new UserExistException("User with id " + userReturnId + " do not exist.");
        }

        if (!listBooks.isBookExistById(bookReturnId)) {
            throw new BookExistException("Book with id " + bookReturnId + " do not exist.");
        }

        if (listBooks.isBookAvailableById(bookReturnId)) {
            throw new BookExistException("Book with id " + bookReturnId + " is available. Can not be returned");
        }

        Record userRecord = getRecordByUserId(userReturnId, bookReturnId, false);

        if (userRecord == null) {
            throw new BookExistException("User: " + userReturnId + " is currently not borrowing a book: " + bookReturnId);
        }

        userRecord.setReturnDate(LocalDate.now());
        Book book = listBooks.getBookById(bookReturnId);
        book.setAvailable(!book.isAvailable());
    }

    public List<Record> getUserRecordList(String userId) {
        if (!listUsers.isUserExistById(userId)) {
            throw new UserExistException("User with id " + userId + " do not exist.");
        }

        List<Record> userRecords = listRecords.stream().filter(r -> r.getUserId().equalsIgnoreCase(userId))
                .collect(Collectors.toList());

        return userRecords;
    }

    public void exportToCSV() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(RECORD_CSV_PATH))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String[] header = {"RecordID", "UserID", "BookID", "Borrow Date", "Return Date"};
            writer.writeNext(header);
            

            for (Record record : listRecords) {
                String returnDateStr = "";
                if (record.getReturnDate() != null) {
                    returnDateStr = record.getReturnDate().format(formatter);
                }

                String[] row = {
                    record.getRecordId(),
                    record.getUserId(),
                    record.getBookId(),
                    record.getBorrowDate().format(formatter),
                    returnDateStr
                };

                writer.writeNext(row);
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (DateTimeException dte) {
            System.err.println(dte.getMessage());
        }
    }

    public void exportToBookCSV() {
        this.listBooks.exportToCSV();
    }

    private Record getRecordByUserId(String userReturnId, String bookReturnId, boolean available) {
        Optional<Record> recordOpt;

        if (available == false) {
            recordOpt = listRecords.stream()
                            .filter(r -> r.getUserId().equalsIgnoreCase(userReturnId) &&
                                        r.getBookId().equalsIgnoreCase(bookReturnId) &&
                                        r.getReturnDate() == null)
                            .findFirst();
        }else {
            recordOpt = listRecords.stream()
                            .filter(r -> r.getUserId().equalsIgnoreCase(userReturnId) &&
                                        r.getBookId().equalsIgnoreCase(bookReturnId) &&
                                        r.getReturnDate() != null)
                            .findFirst();
        }

        return recordOpt.orElse(null);
    }
}

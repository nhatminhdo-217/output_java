package com.example.borrow_records;

import java.time.LocalDate;

import com.example.book.Book;
import com.example.user.User;

public class Record {
    private String recordId;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate; //null nếu chưa trả

    private static final String ID_PATTERN = "^R\\d{3}$";
    public static final int nextId = 1;

    public Record() {};

    public Record(String userId, String bookId, LocalDate borrowDate, LocalDate returnDate) {
        this.recordId = generateNextId();
    }

    public Record(String recordId, String userId, String bookId, LocalDate borrowDate, LocalDate returnDate) {
    }

    public String getRecordId() {
        return recordId;
    }
    public String getUserId() {
        return userId;
    }
    public String getBookId() {
        return bookId;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setRecordId(String recordId) {
        if (recordId.matches(ID_PATTERN)) {
            this.recordId = recordId;
        }else {
            throw new IllegalArgumentException("Invalid record id format (eg: R001)");
        }
    }

    public void setUserId(String userId) {
        if (userId.matches(User.getIdPattern())) {
            this.userId = userId;
        }else {
            throw new IllegalArgumentException("Invalid user id format (eg: U001).");
        }
    }

    public void setBookId(String bookId) {
        if (bookId.matches(Book.getIdPattern())) {
            this.bookId = bookId;
        }else {
            throw new IllegalArgumentException("Invalid book id format (eg: B001)");
        }
    }

    public void setBorrowDate(LocalDate borrowDate) {
        if (borrowDate == null) {
            throw new IllegalArgumentException("Borrow date cannot be null");
        }

        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate == null) {
            throw new IllegalArgumentException("Return date cannot be null");
        }

        if (returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date can not before borrow date");
        }
        this.returnDate = returnDate;
    }

    private String generateNextId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateNextId'");
    }
}

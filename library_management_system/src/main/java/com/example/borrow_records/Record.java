package com.example.borrow_records;

import java.time.LocalDate;
import java.util.List;

import com.example.book.Book;
import com.example.user.User;

public class Record {
  private String recordId;
  private String userId;
  private String bookId;
  private LocalDate borrowDate;
  private LocalDate returnDate; // null nếu chưa trả

  private static final String ID_PATTERN = "^R\\d{3}$";
  private static int nextId = 1;

  public Record() {
  };

  public Record(String userId, String bookId, LocalDate borrowDate, LocalDate returnDate) {
    this.recordId = generateNextId();
    setUserId(userId);
    setBookId(bookId);
    setBorrowDate(borrowDate);
    setReturnDate(returnDate);
  }

  public Record(String recordId, String userId, String bookId, LocalDate borrowDate, LocalDate returnDate) {
    setRecordId(recordId);
    setUserId(userId);
    setBookId(bookId);
    setBorrowDate(borrowDate);
    setReturnDate(returnDate);
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
    } else {
      throw new IllegalArgumentException("Invalid record id format (eg: R001)");
    }
  }

  public void setUserId(String userId) {
    if (userId.matches(User.getIdPattern())) {
      this.userId = userId;
    } else {
      throw new IllegalArgumentException("Invalid user id format (eg: U001).");
    }
  }

  public void setBookId(String bookId) {
    if (bookId.matches(Book.getIdPattern())) {
      this.bookId = bookId;
    } else {
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
    if (returnDate != null &&returnDate.isBefore(borrowDate)) {
      throw new IllegalArgumentException("Return date can not before borrow date");
    }
    this.returnDate = returnDate;
  }

  @Override
  public String toString() {
    System.out.println("--------------------");
    return "Record: " + recordId + "\n"
    + "Borrower ID: " + userId + "\n"
    + "Book ID: " + bookId + "\n"
    + "Borrow Date: " + borrowDate + "\n"
    + "Return Date: " + returnDate;
  }

  private String generateNextId() {
    if (nextId > 999) {
      throw new IllegalStateException("Maxium id!");
    }

    String newId = String.format("R%03d", nextId);
    nextId++;

    if (!newId.matches(ID_PATTERN)) {
      throw new IllegalStateException("Generated id doesn't match pattern: " + newId);
    }

    return newId;
  }

  public static void initializeIdCounter(List<Record> listRecords) {
    if (listRecords == null || listRecords.isEmpty()) {
      nextId = 1;
      return;
    }

    int maxId = 0;
    for (Record record : listRecords) {
      try {
        String numericPart = record.getRecordId().substring(1);
        int recordId = Integer.parseInt(numericPart);
        if (recordId > maxId) {
          maxId = recordId;
        }
      } catch (Exception e) {
        System.err.println("Invalid ID format");
      }
    }

    nextId = maxId + 1;
  }
}

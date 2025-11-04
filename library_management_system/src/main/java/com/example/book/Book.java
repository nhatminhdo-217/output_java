package com.example.book;

import java.util.List;

public class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private double price;
    private boolean available; //true = còn hàng

    private static final String ID_PATTERN =  "^B\\d{3}$";
    private static int nextId = 1;

    //Constructor for auto-gen id
    public Book(String title, String author, int year, double price, boolean available) {
        this.id = generateNextId();
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setPrice(price);
        setAvailable(available);
    }

    //Constructor for import from csv file
    public Book(String id, String title, String author, int year, double price, boolean available) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setYear(year);
        setPrice(price);
        setAvailable(available);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public static String getIdPattern() {
        return ID_PATTERN;
    }

    public void setTitle(String title) {
        if (!title.isEmpty()) {
         this.title = title.trim();   
        }else {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }

    public void setAuthor(String author) {
        if (!author.isEmpty()) {
         this.author = author.trim();   
        }else {
            throw new IllegalArgumentException("Author cannot be null");
        }
    }

    public void setYear(int year) {
        if (year >= 0) {
            this.year = year;
        }else {
            throw new IllegalArgumentException("Year must be greater than 0");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }else {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setId(String id) {
        if (id.matches(ID_PATTERN)) {
            this.id = id;
        }else {
            throw new IllegalArgumentException("Invalid id format (eg: B0001)");
        }
    }

    @Override
    public String toString() {
        System.out.println("--------------------");
        return "Book: " + id + "\n"
        + "Title: " + title + "\n"
        + "Author: " + author + "\n"
        + "Year: " + year + "\n"
        + "Price: $" + price + "\n"
        + "Available: " + available;
    }

    private String generateNextId() {
        if (nextId > 999) {
            throw new IllegalStateException("Maxium id!");
        }

        String newId = String.format("B%03d", nextId);
        nextId++;

        if (!newId.matches(ID_PATTERN)) {
            throw new IllegalStateException("Generated id doesn't match pattern: " + newId);
        }

        return newId;
    }
    
    public static void initializeIdCounter(List<Book> listBooks) {
        if (listBooks == null || listBooks.isEmpty()) {
            nextId = 1;
            return;
        }
        
        int maxId = 0;
        for (Book book : listBooks) {
            try {
                String numericPart = book.getId().substring(1);
                int bookId = Integer.parseInt(numericPart);
                if (bookId > maxId) {
                    maxId = bookId;
                }
            } catch (Exception e) {
                System.err.println("Invalid ID format");
            }
        }

        nextId = maxId + 1;
    }
}

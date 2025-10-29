package stream_api.book_library;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String author;
    private Genre genre;
    private LocalDate publishDate;
    private double price;
    private int pages;

    public Book(int id, String title, String author, Genre genre, LocalDate publishDate, double price, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishDate = publishDate;
        this.price = price;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Genre getGenre() {
        return genre;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    public double getPrice() {
        return price;
    }
    public int getPages() {
        return pages;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
       System.out.println("--------------------");
       return "Book: " + "\n" +
       "ID: " + id + "\n" +
       "Title: " + title + "\n" +
       "Author: " + author + "\n" +
       "Genre: " + genre + "\n" +
       "Publish Date: " + publishDate + "\n" +
       "Price: " + price + "\n" +
       "Pages: " + pages;
    }
}

package com.bashtan.librarry.constructor;



public class Book {
    private String nameBook;
    private String author;
    private int publishingYear;
    private String publishingHouse;
    private String description;
    private int serial;


    public Book(String nameBook, String author, int publishingYear, String publishingHouse, String description, int serial) {
        this.nameBook = nameBook;
        this.author = author;
        this.publishingYear = publishingYear;
        this.publishingHouse = publishingHouse;
        this.description = description;
        this.serial = serial;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

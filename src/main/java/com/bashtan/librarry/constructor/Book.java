package com.bashtan.librarry.constructor;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String nameBook;
    private String author;
    private int publishingYear;
    private String publishingHouse;
    private String description;
    private int serial;
}

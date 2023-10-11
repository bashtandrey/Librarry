package com.bashtan.librarry.constructor;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookT {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nameBook;
    private final SimpleStringProperty author;
    private final SimpleIntegerProperty publishingYear;
    private final SimpleStringProperty publishingHouse;
    private final SimpleStringProperty description;
    private final SimpleIntegerProperty serial;
    private final SimpleBooleanProperty flagBook;

    public BookT(int id, String nameBook, String author, int publishingYear, String publishingHouse, String description, int serial, boolean flagBook) {
        this.id = new SimpleIntegerProperty(id);
        this.nameBook = new SimpleStringProperty(nameBook);
        this.author = new SimpleStringProperty(author);
        this.publishingYear = new SimpleIntegerProperty(publishingYear);
        this.publishingHouse = new SimpleStringProperty(publishingHouse);
        this.description = new SimpleStringProperty(description);
        this.serial = new SimpleIntegerProperty(serial);
        this.flagBook = new SimpleBooleanProperty(flagBook);
    }

    public String getNameBook() {return nameBook.get();}
    public String getAuthor() {return author.get();}
    public int getPublishingYear() {return publishingYear.get();}
    public String getPublishingHouse() {return publishingHouse.get();}
    public String getDescription() {return description.get();}
    public int getSerial() {return serial.get();}
    public int getId() {return id.get();}
    public void setId(int id) {this.id.set(id);}
    public boolean getFlagBook(){return  flagBook.get();}
}

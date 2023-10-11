package com.bashtan.librarry.сonstants;

public class BookConstants {
    public static final int LengthNameBook = 45;
    public static final int LengthAuthor = 45;
    public static final int LengthPublishingYear = 4;
    public static final int LengthPublishingHouse = 65;
    public static final int LengthDescription = 300;
    public static final int LengthSerial = 10;

    public static final String BOOK ="book";
    public static final String ID = "id";
    public static final String NAME_BOOK ="nameBook";
    public static final String AUTHOR ="author";
    public static final String PUBLISHING_YEAR ="publishingYear";
    public static final String PUBLISHING_HOUSE="publishingHouse";
    public static final String DESCRIPTION ="description";
    public static final String SERIAL ="serial";
    public static final String FlAG_BOOK = "flagBook";
    public static final String HUMAN_ID = "humanId";
    public static final String BOOK_FK = "book_FK";
    public static final String BOOK_UN = "book_un";

    public static final String NameBookLabel ="Name Book";
    public static final String AuthorLabel = "Author";
    public static final String PublishingYearLabel ="Publishing Year";
    public static final String PublishingHouseLabel ="Publishing House";
    public static final String SerialLabel ="Serial";
    public static final String DescriptionLabel ="Description";
    public static final String FlagBookLabel="They took";

    public static final String TextAdd ="Add";
    public static final String TextEdit ="Edit";
    public static final String TextDelete ="Delete";
    public static final String TextOk = "Ok";
    public static final String TextCancel ="Cancel";

    public static final String FLAG_BOOK_FALSE ="Not available";
    public static final String FLAG_BOOK_TRUE ="Available";

    public static final String TextIsEmpty ="Field is not Empty";
    public static final String TextLengthNameBook ="The number of characters is not more "+LengthNameBook;
    public static final String TextLengthAuthor ="The number of characters is not more "+LengthAuthor;
    public static final String TextLengthPublishingYear ="Lead a publishing year.\nExample 2022";
    public static final String TextLengthPublishingHouse ="The number of characters is not more "+LengthPublishingHouse;
    public static final String TextLengthSerial ="The number of characters is not more "+LengthSerial;
    public static final String TextLengthDescription ="The number of characters is not more "+LengthDescription;

    public static final String TextIsNumber = "Only numbers";
    public static final String TextIdIsEmpty = "Choose a book";
    public static final String TextSFlagDelete =" It cannot be deleted when the book is in the reader ";
    public static final String TextSFlagEdit =" You cannot edit when the book is in the reader ";

    public static final String TableViewNull = "The database is empty\nstart entering data";
    public static final String TextIsLetters = "Only English Letters";
    public static final String TextSerialFalse =  "The serial number cannot be repeated";
}

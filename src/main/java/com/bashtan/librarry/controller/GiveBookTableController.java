package com.bashtan.librarry.controller;

import com.bashtan.librarry.application.*;
import com.bashtan.librarry.constructor.*;
import com.bashtan.librarry.sql.*;
import com.bashtan.librarry.сonstants.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.sql.SQLException;

public class GiveBookTableController {
    private ObservableList <BookT> bookData;
    private ObservableList <HumanT> humanData;
    private int bookId;
    private int humanId;
    private Date date = new Date(System.currentTimeMillis());
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TableView<BookT> bookTableView = new TableView<BookT>();
    @FXML
    private TableColumn serialTableColumn = new TableColumn();
    @FXML
    private TableColumn nameBookTableColumn = new TableColumn();
    @FXML
    private TableColumn authorTableColumn = new TableColumn();
    @FXML
    private TableColumn publishingYearTableColumn = new TableColumn();
    @FXML
    private TableColumn publishingHouseTableColumn = new TableColumn();
    @FXML
    private TableColumn flagBookTableColumn = new TableColumn();


    @FXML
    private TableView<HumanT> humanTableView = new TableView<HumanT>();
    @FXML
    private TableColumn lastNameTableColumn = new TableColumn();
    @FXML
    private TableColumn firstNameTableColumn = new TableColumn();


    @FXML
    private Label bookTableViewNullLabel;
    @FXML
    private Label humanTableViewNullLabel;
    @FXML
    private Label serialLabel;
    @FXML
    private Label nameBookLabel;
    @FXML
    private Button cancelBookButton;


    @FXML
    private Label lastNameLabel;
    @FXML
    private Label firstNameLabel;

    @FXML
    private Button cancelHumanButton;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @FXML
    void bookTableViewOnKeyPressed(KeyEvent event) {selectBook();}
    @FXML
    void bookTableViewOnMouseClicked(MouseEvent event) {selectBook();}
    @FXML
    void humanTableViewOnKeyPressed(KeyEvent event) {selectHuman();}
    @FXML
    void humanTableViewOnMouseClicked(MouseEvent event) {selectHuman();}



    void bookButtonOk() {
        bookTableView.setVisible(false);
        cancelBookButton.setVisible(true);
        restartHumanTableView();
    }

    @FXML
    void onActionCancelBookButton(ActionEvent event) {
        humanTableView.setVisible(false);
        bookTableView.setVisible(true);
        bookTableView.setDisable(false);
        bookButtonVisible(false);
        bookLabelVisible(false);
    }
    void humanBookOk() {
        humanTableView.setDisable(true);
        cancelHumanButton.setDisable(false);
        cancelHumanButton.setVisible(true);
        cancelBookButton.setDisable(true);
        okButton.setDisable(false);

    }
    @FXML
    void onActionCancelHumanButton(ActionEvent event) {
        humanLabelVisible(false);
        humanTableView.setDisable(false);
        cancelHumanButton.setVisible(false);
        cancelBookButton.setDisable(false);
        okButton.setDisable(true);
    }

    @FXML
    void onActionOkButton(ActionEvent event) {
        new GiveBookSql().humanId_flagBook_flagHuman(humanId, bookId,true,true);
        new GiveBookSql().giveBookAdd(humanId,bookId, UserSql.getUser().getId(),date);
        Message.alertInformation(GiveBookTableConstants.TITLE,GiveBookTableConstants.GIVE_BOOK_OK);
        new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
    }

    @FXML
    void onActionCancelButton(ActionEvent event) {new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");}

    private void bookTableViewNull(boolean bol){
        bookTableView.setVisible(bol);
        bookTableViewNullLabel.setVisible(!bol);
        bookTableViewNullLabel.setText(GiveBookTableConstants.BOOK_TableView_Null);
    }
    private void humanTableViewNull(boolean bol){
        humanTableView.setVisible(bol);
        humanTableViewNullLabel.setVisible(!bol);
        humanTableViewNullLabel.setText(GiveBookTableConstants.HUMAN_TableView_Null);
    }
    private void restartBookTableView() {
        try {
            bookData = new BookSql().observableListFalse();
            if (bookData.isEmpty()){
                bookTableViewNull(false);
            } else {
                bookTableViewNull(true);
                bookTableView.setItems(bookData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void restartHumanTableView() {
        try {
            humanData = new HumanSql().observableList();
            if (humanData.isEmpty()){
                humanTableViewNull(false);
            }else {
                humanTableViewNull(true);
                humanTableView.setItems(humanData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void selectBook() {
        if (!bookTableView.getSelectionModel().isEmpty()) {
            BookT bookT = bookTableView.getSelectionModel().getSelectedItem();
            String headerText =
                    BookConstants.SerialLabel + ": " + bookT.getSerial() + "\r" +
                    BookConstants.NameBookLabel +": " + bookT.getNameBook();
            boolean result = Message.alertConfirmation(GiveBookTableConstants.BOOK_TITLE,headerText,GiveBookTableConstants.OK_BOOK_BUTTON);
            if (result){
                setDataBook(bookT.getId(), String.valueOf(bookT.getSerial()), bookT.getNameBook());
                bookLabelVisible(true);
                bookButtonOk();
            }
        }else Message.alertInformation(GiveBookTableConstants.BOOK_TITLE,GiveBookTableConstants.BOOK_NOT_SELECTED_TEXT);
    }
    private void selectHuman() {
        if (!humanTableView.getSelectionModel().isEmpty()) {
            HumanT humanT = humanTableView.getSelectionModel().getSelectedItem();
            String headerText =
                    HumanConstants.LastNameLabel + ": " + humanT.getLastName() + "\r" +
                    HumanConstants.FirstNameLabel +": " + humanT.getFirstName();
            boolean result = Message.alertConfirmation(GiveBookTableConstants.HUMAN_TITLE,headerText,GiveBookTableConstants.OK_HUMAN_BUTTON);
            if (result){
                setDataHuman(humanT.getId(),humanT.getLastName(),humanT.getFirstName());
                humanLabelVisible(true);
                humanBookOk();
            }
        }else Message.alertInformation(GiveBookTableConstants.HUMAN_TITLE,GiveBookTableConstants.HUMAN_NOT_SELECTED_TEXT);

    }
    private void setDataBook(int id, String serial, String nameBook){
        serialLabel.setText(serial);
        nameBookLabel.setText(nameBook);
        bookId =id;
    }
    private void setDataHuman(int id, String lastName, String firstName){
        lastNameLabel.setText(lastName);
        firstNameLabel.setText(firstName);
        humanId =id;
    }


    private void bookLabelVisible(boolean bool){
        serialLabel.setVisible(bool);
        nameBookLabel.setVisible(bool);
    }
    private void bookButtonVisible(boolean bool){
        cancelBookButton.setVisible(bool);
    }
    private void humanLabelVisible(boolean bool){
        lastNameLabel.setVisible(bool);
        firstNameLabel.setVisible(bool);
    }
    private void humanButtonVisible(boolean bool){
        cancelHumanButton.setVisible(bool);

    }

    private void start(){
        humanTableView.setVisible(false);
        okButton.setDisable(true);
        bookLabelVisible(false);
        bookButtonVisible(false);
        humanLabelVisible(false);
        humanButtonVisible(false);
        restartBookTableView();
    }

    @FXML
    void initialize() {
        titleLabel.setText(GiveBookTableConstants.TITLE);
        cancelBookButton.setText(GiveBookTableConstants.CANCEL_BOOK_BUTTON);
        cancelHumanButton.setText(GiveBookTableConstants.CANCEL_HUMAN_BUTTON);
        okButton.setText(GiveBookTableConstants.OK_BUTTON);
        cancelButton.setText(GiveBookTableConstants.CANCEL_BUTTON);

        serialTableColumn.setText(BookConstants.SerialLabel);
        serialTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,Integer>(BookConstants.SERIAL));
        nameBookTableColumn.setText(BookConstants.NameBookLabel);
        nameBookTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,String>(BookConstants.NAME_BOOK));
        authorTableColumn.setText(BookConstants.AuthorLabel);
        authorTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,String>(BookConstants.AUTHOR));
        publishingYearTableColumn.setText(BookConstants.PublishingYearLabel);
        publishingYearTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,Integer>(BookConstants.PUBLISHING_YEAR));
        publishingHouseTableColumn.setText(BookConstants.PublishingHouseLabel);
        publishingHouseTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,String>(BookConstants.PUBLISHING_HOUSE));
        flagBookTableColumn.setText(BookConstants.FlagBookLabel);
        flagBookTableColumn.setCellValueFactory(new PropertyValueFactory<BookT,Boolean>(BookConstants.FlAG_BOOK));

        lastNameTableColumn.setText(HumanConstants.LastNameLabel);
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.LAST_NAME));
        firstNameTableColumn.setText(HumanConstants.FirstNameLabel);
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.FIRST_NAME));
        start();
    }
}
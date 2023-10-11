package com.bashtan.librarry.controller;

import  java.sql.SQLException;
import java.util.function.UnaryOperator;

import com.bashtan.librarry.application.*;
import com.bashtan.librarry.constructor.*;
import com.bashtan.librarry.sql.BookSql;
import com.bashtan.librarry.сonstants.BookConstants;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
public class BookTableController {

    private ObservableList bookData;
    private final ToggleGroup actionGroup = new ToggleGroup();
    private int id = 0;
    private boolean flagBook;
    private static String errorTextMessageTitle;
    private static String secondSerial;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<BookT> tableView = new TableView<BookT>();
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
    private TextField nameBookTextField;
    @FXML
    private TextField publishingYearTextField;
    @FXML
    private TextField publishingHouseTextField;
    @FXML
    private TextField serialTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label nameBookLengthLabel;
    @FXML
    private Label authorLengthLabel;
    @FXML
    private Label publishingYearLengthLabel;
    @FXML
    private Label publishingHouseLengthLabel;
    @FXML
    private Label serialLengthLabel;
    @FXML
    private Label descriptionLengthLabel;
    @FXML
    private Label tableViewNullLabel;


    @FXML
    private RadioButton addRadioButton;
    @FXML
    private RadioButton editRadioButton;
    @FXML
    private RadioButton deleteRadioButton;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;


    @FXML
    void onActionCancelButton(ActionEvent event) {
        new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
    }

    @FXML
    void onActionOkButton(ActionEvent event) {

        Object command = actionGroup.getSelectedToggle();

        if (command == addRadioButton) {
            new BookSql().addDB(getBookTextField());
            restartTableView();
            disable(true);
            reset();
            actionGroupFalse();
        } else if (command == editRadioButton) {
            new BookSql().editDB(id, getBookTextField());
            restartTableView();
            disable(false);
            reset();
        } else if (command == deleteRadioButton) {
            new BookSql().deleteDB(id);
            restartTableView();
            disable(false);
            reset();
        }
    }

    @FXML
    void onActionAddRadioButton(ActionEvent event) {
        id = 0;
        reset();
        disable(false);
        okButton.setText(BookConstants.TextAdd);
        nameBookTextField.requestFocus();
        secondSerial = "";
    }

    @FXML
    void onActionEditRadioButton(ActionEvent event) {
        if (id != 0 & flagBook == false) {
            disable(false);
            okButton.setText(BookConstants.TextEdit);
            nameBookTextField.requestFocus();
        } else {
            reset();
            actionGroupFalse();
            disable(true);
            if (flagBook) Message.alertError(BookConstants.TextDelete, BookConstants.TextSFlagEdit);
            else Message.alertError(BookConstants.TextEdit, BookConstants.TextIdIsEmpty);
        }
    }

    @FXML
    void onActionDeleteRadioButton(ActionEvent event) {
        if (id != 0 & flagBook == false) {
            okButton.setText(BookConstants.TextDelete);
            disable(true);
            okButton.setDisable(false);
        } else {
            reset();
            actionGroupFalse();
            disable(true);
            if (flagBook) Message.alertError(BookConstants.TextDelete, BookConstants.TextSFlagDelete);
            else Message.alertError(BookConstants.TextDelete, BookConstants.TextIdIsEmpty);
        }
    }


    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {
        select();
    }

    @FXML
    void tableViewOnKeyPressed(KeyEvent event) {
        select();
    }

    @FXML
    void onActonNameBookTextField(ActionEvent event) {
        Check.check(nameBookTextField, authorTextField, BookConstants.NameBookLabel, BookConstants.TextIsEmpty);
    }

    @FXML
    void onActionAuthorTextField(ActionEvent event) {
        Check.check(authorTextField, publishingYearTextField, BookConstants.AuthorLabel, BookConstants.TextIsEmpty);
    }

    @FXML
    void onActionPublishingYearTextField(ActionEvent event) {
        Check.check(publishingYearTextField, publishingHouseTextField, BookConstants.PublishingYearLabel, BookConstants.TextIsEmpty);
    }

    @FXML
    void onActionPublishingHouseTextField(ActionEvent event) {
        Check.check(publishingHouseTextField, serialTextField, BookConstants.PublishingHouseLabel, BookConstants.TextIsEmpty);
    }

    @FXML
    void onActionSerialTextField(ActionEvent event) {
        if (bookData.isEmpty()) {
            Check.check(true, serialTextField, descriptionTextArea, okButton, BookConstants.SerialLabel, BookConstants.TextIsEmpty);
        } else
            Check.check(serialCheck(), serialTextField, descriptionTextArea, okButton, BookConstants.SerialLabel, BookConstants.TextIsEmpty);
    }

    @FXML
    void onKeyReleasedNameBookTextField(KeyEvent event) {
        errorTextMessageTitle = BookConstants.NameBookLabel;
        Check.checkLength(nameBookTextField, nameBookLengthLabel, BookConstants.LengthNameBook, BookConstants.NameBookLabel, BookConstants.TextLengthNameBook);
        Check.checkCharacter(nameBookTextField, lettersFilter);
    }

    @FXML
    void onKeyReleasedAuthorTextField(KeyEvent event) {
        errorTextMessageTitle = BookConstants.AuthorLabel;
        Check.checkLength(authorTextField, authorLengthLabel, BookConstants.LengthAuthor, BookConstants.AuthorLabel, BookConstants.TextLengthAuthor);
        Check.checkCharacter(authorTextField, lettersFilter);
    }

    @FXML
    void onKeyReleasedPublishingYearTextField(KeyEvent event) {
        errorTextMessageTitle = BookConstants.PublishingYearLabel;
        Check.checkLength(publishingYearTextField, publishingYearLengthLabel, BookConstants.LengthPublishingYear, BookConstants.PublishingYearLabel, BookConstants.TextLengthPublishingYear);
        Check.checkCharacter(publishingYearTextField, integerFilter);
    }

    @FXML
    void onKeyReleasedPublishingHouseTextField(KeyEvent event) {
        errorTextMessageTitle = BookConstants.PUBLISHING_HOUSE;
        Check.checkLength(publishingHouseTextField, publishingHouseLengthLabel, BookConstants.LengthPublishingHouse, BookConstants.PublishingHouseLabel, BookConstants.TextLengthPublishingHouse);
        Check.checkCharacter(publishingHouseTextField, lettersFilter);
    }

    @FXML
    void onKeyReleasedSerialTextField(KeyEvent event) {
        errorTextMessageTitle = BookConstants.SerialLabel;
        Check.checkLength(serialTextField, serialLengthLabel, BookConstants.LengthSerial, BookConstants.SerialLabel, BookConstants.TextLengthSerial);
        Check.checkCharacter(serialTextField, integerFilter);
    }

    @FXML
    void onKeyReleasedDescriptionTextArea(KeyEvent event) {
        Check.checkLength(descriptionTextArea, descriptionLengthLabel, BookConstants.LengthDescription, BookConstants.DescriptionLabel, BookConstants.TextLengthDescription);
    }

    private void select() {
        BookT bookT = tableView.getSelectionModel().getSelectedItem();
        setData(bookT.getNameBook(), bookT.getAuthor(), String.valueOf(bookT.getPublishingYear()), bookT.getPublishingHouse(), bookT.getDescription(), String.valueOf(bookT.getSerial()));
        id = bookT.getId();
        flagBook = bookT.getFlagBook();
        secondSerial = String.valueOf(bookT.getSerial());
        disable(true);
        actionGroupFalse();
        setLengthLabel();
    }

    private void actionGroupFalse() {
        if (!(actionGroup.getSelectedToggle() == null)) actionGroup.getSelectedToggle().setSelected(false);
    }

    private void setData(String nameBook, String author, String pYear, String pHouse, String description, String serial) {
        nameBookTextField.setText(nameBook);
        authorTextField.setText(author);
        publishingHouseTextField.setText(pHouse);
        publishingYearTextField.setText(pYear);
        serialTextField.setText(serial);
        descriptionTextArea.setText(description);
    }

    private void setLengthLabel() {
        Check.checkLength(nameBookTextField, nameBookLengthLabel, BookConstants.LengthNameBook, BookConstants.NameBookLabel, BookConstants.TextLengthNameBook);
        Check.checkLength(authorTextField, authorLengthLabel, BookConstants.LengthAuthor, BookConstants.AuthorLabel, BookConstants.TextLengthAuthor);
        Check.checkLength(publishingYearTextField, publishingYearLengthLabel, BookConstants.LengthPublishingYear, BookConstants.PublishingYearLabel, BookConstants.TextLengthPublishingYear);
        Check.checkLength(publishingHouseTextField, publishingHouseLengthLabel, BookConstants.LengthPublishingHouse, BookConstants.PublishingHouseLabel, BookConstants.TextLengthPublishingHouse);
        Check.checkLength(serialTextField, serialLengthLabel, BookConstants.LengthSerial, BookConstants.SerialLabel, BookConstants.TextLengthSerial);
        Check.checkLength(descriptionTextArea, descriptionLengthLabel, BookConstants.LengthDescription, BookConstants.DescriptionLabel, BookConstants.TextLengthDescription);
    }

    private boolean serialCheck() {
        if (secondSerial.equals(serialTextField.getText())) {
            return true;
        } else return false;
    }

    private void disable(boolean bool) {
        nameBookTextField.setDisable(bool);
        authorTextField.setDisable(true);
        publishingHouseTextField.setDisable(true);
        publishingYearTextField.setDisable(true);
        serialTextField.setDisable(true);
        descriptionTextArea.setDisable(true);
        okButton.setDisable(true);
    }

    private void reset() {
        setData(null, null, null, null, null, null);
        nameBookLengthLabel.setText(null);
        authorLengthLabel.setText(null);
        publishingYearLengthLabel.setText(null);
        publishingHouseLengthLabel.setText(null);
        serialLengthLabel.setText(null);
        descriptionLengthLabel.setText(null);

    }


    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9]*")) {
            return change;
        }
        Message.alertInformation(errorTextMessageTitle, BookConstants.TextIsNumber);
        return null;
    };

    UnaryOperator<TextFormatter.Change> lettersFilter = change -> {
        String input = change.getText();
        if (input.matches("[A-Z]*[a-z]*")) {
            return change;
        }
        Message.alertInformation(errorTextMessageTitle, BookConstants.TextIsLetters);
        return null;
    };

    private void tableViewNull(boolean bol) {
        tableView.setVisible(bol);
        editRadioButton.setVisible(bol);
        deleteRadioButton.setVisible(bol);
        tableViewNullLabel.setVisible(!bol);
        tableViewNullLabel.setText(BookConstants.TableViewNull);
    }

    private void restartTableView() {
        try {
            bookData = new BookSql().observableList();
            if (bookData.isEmpty()) {
                tableViewNull(false);
            } else {
                tableViewNull(true);
                tableView.setItems(bookData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Book getBookTextField() {
        return new Book(nameBookTextField.getText(),
                authorTextField.getText(),
                Integer.parseInt(publishingYearTextField.getText()),
                publishingHouseTextField.getText(),
                descriptionTextArea.getText(),
                Integer.parseInt(serialTextField.getText()));
    }

    @FXML
    void initialize() {
        disable(true);

        addRadioButton.setToggleGroup(actionGroup);
        editRadioButton.setToggleGroup(actionGroup);
        deleteRadioButton.setToggleGroup(actionGroup);
        addRadioButton.setText(BookConstants.TextAdd);
        editRadioButton.setText(BookConstants.TextEdit);
        deleteRadioButton.setText(BookConstants.TextDelete);
        cancelButton.setText(BookConstants.TextCancel);
        okButton.setText(BookConstants.TextOk);
        nameBookTextField.setPromptText(BookConstants.NameBookLabel);
        authorTextField.setPromptText(BookConstants.AuthorLabel);
        publishingYearTextField.setPromptText(BookConstants.PublishingYearLabel);
        publishingHouseTextField.setPromptText(BookConstants.PublishingHouseLabel);
        serialTextField.setPromptText(BookConstants.SerialLabel);
        descriptionTextArea.setPromptText(BookConstants.DescriptionLabel);

        serialTableColumn.setText(BookConstants.SerialLabel);
        serialTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, Integer>(BookConstants.SERIAL));
        nameBookTableColumn.setText(BookConstants.NameBookLabel);
        nameBookTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, String>(BookConstants.NAME_BOOK));
        authorTableColumn.setText(BookConstants.AuthorLabel);
        authorTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, String>(BookConstants.AUTHOR));
        publishingYearTableColumn.setText(BookConstants.PublishingYearLabel);
        publishingYearTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, Integer>(BookConstants.PUBLISHING_YEAR));
        publishingHouseTableColumn.setText(BookConstants.PublishingHouseLabel);
        publishingHouseTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, String>(BookConstants.PUBLISHING_HOUSE));
        flagBookTableColumn.setText(BookConstants.FlagBookLabel);
        flagBookTableColumn.setCellValueFactory(new PropertyValueFactory<BookT, Boolean>(BookConstants.FlAG_BOOK));
        restartTableView();
    }
    }

package com.bashtan.librarry.controller;

import java.sql.Date;
import java.sql.SQLException;

import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.Message;
import com.bashtan.librarry.constructor.BookT;
import com.bashtan.librarry.constructor.HumanT;
import com.bashtan.librarry.sql.BookSql;
import com.bashtan.librarry.sql.GiveBookSql;
import com.bashtan.librarry.sql.HumanSql;
import com.bashtan.librarry.sql.UserSql;
import com.bashtan.librarry.сonstants.BookConstants;
import com.bashtan.librarry.сonstants.GiveBookTableConstants;
import com.bashtan.librarry.сonstants.HumanConstants;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

    public class GiveBookTableControllerOld {
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
        private Label selectedBookTitleLabel;
        @FXML
        private Label bookTableViewNullLabel;
        @FXML
        private Label humanTableViewNullLabel;
        @FXML
        private Label serialLabel;
        @FXML
        private Label nameBookLabel;
        @FXML
        private Button okBookButton;
        @FXML
        private Button cancelBookButton;

        @FXML
        private Label selectedHumanTitleLabel;
        @FXML
        private Label lastNameLabel;
        @FXML
        private Label firstNameLabel;
        @FXML
        private Button okHumanButton;
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


        @FXML
        void onActionOkBookButton(ActionEvent event) {
            bookTableView.setDisable(true);
            okBookButton.setVisible(false);
            cancelBookButton.setVisible(true);
            restartHumanTableView();
        }

        @FXML
        void onActionCancelBookButton(ActionEvent event) {
            humanTableView.setVisible(false);
            bookTableView.setDisable(false);
            bookButtonVisible(false);
            bookLabelVisible(false);
        }
        @FXML
        void onActionOkHumanButton(ActionEvent event) {
            humanTableView.setDisable(true);
            okHumanButton.setVisible(false);
            cancelHumanButton.setVisible(true);
            okButton.setDisable(false);

        }
        @FXML
        void onActionCancelHumanButton(ActionEvent event) {
            humanTableView.setDisable(false);
            okButton.setDisable(true);
        }

        @FXML
        void onActionOkButton(ActionEvent event) {
//            new GiveBookSql().humanId_flagBook_flagHuman(humanId, bookId,true,true);
//            new GiveBookSql().giveBookAdd(humanId, bookId, UserSql.getUser().getId(),date);
//            Message.alertInformation(GiveBookTableConstants.TITLE,GiveBookTableConstants.GIVE_BOOK_OK);
//            start();
//            bookTableView.setDisable(false);
//            new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
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
                setDataBook(bookT.getId(), String.valueOf(bookT.getSerial()), bookT.getNameBook());
                bookLabelVisible(true);
                okBookButton.setVisible(true);
            }
        }
        private void selectHuman() {
            if (!humanTableView.getSelectionModel().isEmpty()) {
                HumanT humanT = humanTableView.getSelectionModel().getSelectedItem();
                setDataHuman(humanT.getId(),humanT.getLastName(),humanT.getFirstName());
                humanLabelVisible(true);
                bookButtonVisible(false);
                okHumanButton.setVisible(true);
            }
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
            selectedBookTitleLabel.setVisible(bool);
            serialLabel.setVisible(bool);
            nameBookLabel.setVisible(bool);
        }
        private void bookButtonVisible(boolean bool){
            okBookButton.setVisible(bool);
            cancelBookButton.setVisible(bool);
        }
        private void humanLabelVisible(boolean bool){
            selectedHumanTitleLabel.setVisible(bool);
            lastNameLabel.setVisible(bool);
            firstNameLabel.setVisible(bool);
        }
        private void humanButtonVisible(boolean bool){
            okHumanButton.setVisible(bool);
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
            selectedBookTitleLabel.setText(GiveBookTableConstants.BOOK_TITLE);
            selectedHumanTitleLabel.setText(GiveBookTableConstants.HUMAN_TITLE);
            okBookButton.setText(GiveBookTableConstants.OK_BOOK_BUTTON);
            cancelBookButton.setText(GiveBookTableConstants.CANCEL_BOOK_BUTTON);
            okHumanButton.setText(GiveBookTableConstants.OK_HUMAN_BUTTON);
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

//            start();
        }
    }
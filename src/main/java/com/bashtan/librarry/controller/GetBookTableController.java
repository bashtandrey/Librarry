package com.bashtan.librarry.controller;

import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.Message;
import com.bashtan.librarry.constructor.BookT;
import com.bashtan.librarry.constructor.HumanT;
import com.bashtan.librarry.sql.BookSql;
import com.bashtan.librarry.sql.GetBookSql;
import com.bashtan.librarry.sql.HumanSql;
import com.bashtan.librarry.сonstants.BookConstants;
import com.bashtan.librarry.сonstants.GetBookTableConstants;
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

import java.sql.SQLException;

public class GetBookTableController {
    private ObservableList <HumanT> humanData;
    private ObservableList <BookT> bookData;
    private int flagUnselectButton;
    private String humanHeaderText;
    private String bookHeaderText;
    private int humanId;
    private int bookId;


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<HumanT> humanTableView = new TableView();
    @FXML
    private TableColumn lastNameTableColumn = new TableColumn();
    @FXML
    private TableColumn firstNameTableColumn = new TableColumn();

    @FXML
    private TableView<BookT> bookTableView = new TableView();
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
    private Label humanTableViewNullLabel;
    @FXML
    private Label humanLabel;
    @FXML
    private Label bookLabel;

    @FXML
    private Button unselectButton;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @FXML
    void humanTableViewOnKeyPressed(KeyEvent event) {selectHuman();}
    @FXML
    void humanTableViewOnMouseClicked(MouseEvent event) {selectHuman();}

    @FXML
    void bookTableViewOnKeyPressed(KeyEvent event) {selectBook();}
    @FXML
    void bookTableViewOnMouseClicked(MouseEvent event) {selectBook();}

    @FXML
    void onActionOkButton(ActionEvent event){
        int sum = new GetBookSql().sumHumanWithBook(humanId);
        int giveBookId = new GetBookSql().giveBookId(humanId,bookId);
        new GetBookSql().deleteGiveBook(giveBookId);
        new GetBookSql().editBookDB(bookId);
        if (sum==1){
            new GetBookSql().editHumanDB(humanId);
        }
        new Message().alertInformation(GetBookTableConstants.TITLE,GetBookTableConstants.TEXT);
        new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
    }
    @FXML
    void onActionCancelButton(ActionEvent event) {new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");}

    @FXML
    void onActionUnselectButton(ActionEvent event){
        switch (flagUnselectButton){
            case 0:{break;}
            case 1:{
                bookTableView.setVisible(false);
                humanTableView.setVisible(true);
                restartHumanTableView();
                humanLabel.setText(null);
                unselectButton(null,false);
                flagUnselectButton=0;
                humanId = 0;
                break;
            }
            case 2:{
                bookLabel.setText(null);
                okButton.setDisable(true);
                flagUnselectButton=1;
                unselectButton(GetBookTableConstants.HUMAN_UNSELECT_BUTTON,true);
                bookId = 0;
                break;
            }

        }

    }


    private void selectHuman() {
        if (!humanTableView.getSelectionModel().isEmpty()) {
            HumanT humanT = humanTableView.getSelectionModel().getSelectedItem();
            humanHeaderText =
                    HumanConstants.LastNameLabel + ": " + humanT.getLastName() + "\n" +
                            HumanConstants.FirstNameLabel +": " + humanT.getFirstName();
            boolean result = Message.alertConfirmation(GiveBookTableConstants.HUMAN_TITLE,humanHeaderText,GiveBookTableConstants.OK_HUMAN_BUTTON);
            if (result){
                restartBookTableViewWithId(humanT.getId());
                humanBookOk();
                humanLabel.setText(humanHeaderText);
                unselectButton(GetBookTableConstants.HUMAN_UNSELECT_BUTTON,true);
                flagUnselectButton = 1;
                humanId = humanT.getId();
            }
        }else Message.alertInformation(GiveBookTableConstants.HUMAN_TITLE,GiveBookTableConstants.HUMAN_NOT_SELECTED_TEXT);
    }

    private void unselectButton(String title, boolean bol){
        unselectButton.setText(title);
        unselectButton.setVisible(bol);
    }

    private void selectBook() {
        if (!bookTableView.getSelectionModel().isEmpty()) {
            BookT bookT = bookTableView.getSelectionModel().getSelectedItem();
            bookHeaderText =
                    BookConstants.SerialLabel + ": " + bookT.getSerial() + "\n" +
                            BookConstants.NameBookLabel +": " + bookT.getNameBook();
            boolean result = Message.alertConfirmation(GiveBookTableConstants.BOOK_TITLE,bookHeaderText,GiveBookTableConstants.OK_BOOK_BUTTON);
            if (result){
                bookLabel.setText(bookHeaderText);
                unselectButton.setText(GetBookTableConstants.BOOK_UNSELECT_BUTTON);
                flagUnselectButton = 2;
                bookId = bookT.getId();
                okButton.setDisable(false);
            }
        }else Message.alertInformation(GiveBookTableConstants.BOOK_TITLE,GiveBookTableConstants.BOOK_NOT_SELECTED_TEXT);
    }

    private void restartHumanTableView() {
        try {
            humanData = new HumanSql().observableListWithBook();
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

    private void restartBookTableViewWithId(int id) {
        try {
            bookData = new BookSql().observableListWithHuman(id);
            bookTableView.setItems(bookData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void humanTableViewNull(boolean bol){
        humanTableView.setVisible(bol);
        humanTableViewNullLabel.setVisible(!bol);
        humanTableViewNullLabel.setText(GetBookTableConstants.HUMAN_TableView_Null);
    }

    private void start(){
        okButton.setDisable(true);
        flagUnselectButton =0;
        unselectButton(null,false);
        restartHumanTableView();
        humanLabel.setText(null);
        bookLabel.setText(null);
    }
    void humanBookOk() {
        humanTableView.setVisible(false);
        bookTableView.setVisible(true);

    }

    @FXML
    void initialize() {
        lastNameTableColumn.setText(HumanConstants.LastNameLabel);
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.LAST_NAME));
        firstNameTableColumn.setText(HumanConstants.FirstNameLabel);
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.FIRST_NAME));

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

        okButton.setText(GetBookTableConstants.OK_BUTTON);
        cancelButton.setText(GetBookTableConstants.CANCEL_BUTTON);
        start();
    }

}

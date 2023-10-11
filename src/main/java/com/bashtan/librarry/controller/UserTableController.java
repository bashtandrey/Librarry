package com.bashtan.librarry.controller;

import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.Check;
import com.bashtan.librarry.application.Coder;
import com.bashtan.librarry.application.Message;
import com.bashtan.librarry.constructor.User;
import com.bashtan.librarry.constructor.UserT;
import com.bashtan.librarry.sql.UserSql;
import com.bashtan.librarry.сonstants.BookConstants;
import com.bashtan.librarry.сonstants.UserConstants;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserTableController {
    private final ToggleGroup actionGroup = new ToggleGroup();
    private final ToggleGroup typeGroup = new ToggleGroup();
    private ObservableList userData;
    private int id =0;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane radioButtonGridPane;

    @FXML
    private TableView<UserT> tableView = new TableView<UserT>();
    @FXML
    private TableColumn lastNameTableColumn = new TableColumn();
    @FXML
    private TableColumn firstNameTableColumn = new TableColumn();
    @FXML
    private TableColumn typeTableColumn = new TableColumn();

    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label lastNameLengthLabel;
    @FXML
    private Label firstNameLengthLabel;
    @FXML
    private Label loginLengthLabel;
    @FXML
    private Label passwordLengthLabel;


    @FXML
    private Label tableViewNullLabel;


    @FXML
    private RadioButton addRadioButton;
    @FXML
    private RadioButton editRadioButton;
    @FXML
    private RadioButton deleteRadioButton;

    @FXML
    private RadioButton adminRadioButton;
    @FXML
    private RadioButton pastorRadioButton;
    @FXML
    private RadioButton librarianRadioButton;

    @FXML
    private Button actionButton;
    @FXML
    private Button cancelButton;


    @FXML
    void onActionCancelButton(ActionEvent event) {
        new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
    }

    @FXML
    void onActionButton(ActionEvent event) throws NoSuchAlgorithmException {
        Object command = actionGroup.getSelectedToggle();
        if(command.equals(addRadioButton)){
            new UserSql().addDB(getUserTextField());
            reset();
            restartTableView();
        } else if(command.equals(editRadioButton)){
            new UserSql().editDB(id,getUserTextField());
            reset();
            restartTableView();
        } else if(command.equals(deleteRadioButton)){
            new UserSql().deleteDB(id);
            reset();
            restartTableView();
        }

    }
        @FXML
        void onActionAddRadioButton (ActionEvent event){
            id = 0;
            reset();
            disable(false);
            actionButton.setText(UserConstants.TextAdd);
            lastNameTextField.requestFocus();
        }

        @FXML
        void onActionEditRadioButton (ActionEvent event){
            if (id != 0 ) {
                disable(false);
                actionButton.setText(UserConstants.TextEdit);
                lastNameTextField.requestFocus();
            } else {
                reset();
                actionGroupFalse();
                disable(true);
                Message.alertError(UserConstants.TextEdit, UserConstants.TextIdIsEmpty);
            }
        }

        @FXML
        void onActionDeleteRadioButton (ActionEvent event){
            if (id != 0) {
                actionButton.setText(UserConstants.TextDelete);
                disable(true);
                actionButton.setDisable(false);
            } else {
                reset();
                actionGroupFalse();
                disable(true);
                Message.alertError(UserConstants.TextDelete, UserConstants.TextIdIsEmpty);
            }
        }

    @FXML
    void onActonAdminRadioButton (ActionEvent event){
        actionButton.setDisable(false);
    }
    @FXML
    void onActionPastorRadioButton (ActionEvent event){
        actionButton.setDisable(false);
    }
    @FXML
    void onActionLibrarianRadioButton (ActionEvent event){
        actionButton.setDisable(false);
    }
    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {select();}
    @FXML
    void tableViewOnKeyPressed(KeyEvent event) {select();}

    @FXML
    void onActionLastNameTextField(ActionEvent event) {
        Check.checkUser(lastNameTextField,firstNameTextField,UserConstants.USER_LAST_NAME,UserConstants.TextIsEmpty);
    }

    @FXML
    void onActonFirstNameTextField(ActionEvent event) {
        Check.checkUser(firstNameTextField,loginTextField,UserConstants.USER_FIRST_NAME,UserConstants.TextIsEmpty);
    }
    @FXML
    void onActionLoginTextField(ActionEvent event) {
        Check.checkUser(loginTextField,passwordPasswordField, UserConstants.USER_LOGIN,UserConstants.TextIsEmpty);
    }
    @FXML
    void onActionPasswordPasswordField(ActionEvent event) {
        if (!(typeGroup.getSelectedToggle() == null)) {
            if (typeGroup.getSelectedToggle().isSelected()) {
                radioButtonGridPane.setDisable(false);
                actionButton.setDisable(false);
                actionButton.requestFocus();
            }
        } else
            Check.checkUser(passwordPasswordField, radioButtonGridPane, UserConstants.USER_PASSWORD, UserConstants.TextIsEmpty);
    }
    @FXML
    void onKeyReleasedLastNameTextField(KeyEvent  event) {
        Check.checkLength(lastNameTextField,lastNameLengthLabel,UserConstants.LengthUserLastName,UserConstants.USER_LAST_NAME,UserConstants.TextLengthLastName);
    }

    @FXML
    void onKeyReleasedFirstNameTextField(KeyEvent  event) {
        Check.checkLength(firstNameTextField,firstNameLengthLabel,UserConstants.LengthUserFirstName,UserConstants.USER_FIRST_NAME,UserConstants.TextLengthFirstName);
    }

    @FXML
    void onKeyReleasedLoginTextField(KeyEvent  event) {
        Check.checkLength(loginTextField,loginLengthLabel,UserConstants.LengthLogin,UserConstants.USER_LOGIN,UserConstants.TextLengthLogin);
    }

    @FXML
    void onKeyReleasedPasswordPasswordField(KeyEvent  event) {
        Check.checkLength(passwordPasswordField,passwordLengthLabel,UserConstants.LengthPassword,UserConstants.USER_PASSWORD,UserConstants.TextLengthPassword);
    }

    private void select() {
        UserT userT = tableView.getSelectionModel().getSelectedItem();
        setData(userT.getUserLastName(), userT.getUserFirstName(),userT.getLevel());
        id = userT.getId();
        disable(true);
        actionGroupFalse();
        lengthReset();
        setLengthLabel();
    }

    public void typeStringInRadioButton(String sting) {
        if (sting == null) typeGroupFalse();
        else {
            switch (sting) {
                case "null": {
                    typeGroupFalse();
                    break;
                }
                case "Admin": {
                    adminRadioButton.setSelected(true);
                    break;
                }
                case "Pastor": {
                    pastorRadioButton.setSelected(true);
                    break;
                }
                case "Librarian": {
                    librarianRadioButton.setSelected(true);
                    break;
                }
            }
        }
    }

    public int typeRadioButtonInInt() {
        Object command = typeGroup.getSelectedToggle();
        if(command.equals(adminRadioButton)){
         return 1;
        } else if(command.equals(pastorRadioButton)){
            return 2;
        } else if(command.equals(librarianRadioButton)){
            return 3;
        } else return 0;
    }
    private void actionGroupFalse(){if (!(actionGroup.getSelectedToggle() == null))actionGroup.getSelectedToggle().setSelected(false);}
    private void typeGroupFalse(){if (!(typeGroup.getSelectedToggle() == null))typeGroup.getSelectedToggle().setSelected(false);}


    private void setData(String userLastName,String userFirstName,String level){
        lastNameTextField.setText(userLastName);
        firstNameTextField.setText(userFirstName);
        loginTextField.setVisible(false);
        passwordPasswordField.setVisible(false);
        typeStringInRadioButton(level);


    }
    private void setLengthLabel(){
        Check.checkLength(lastNameTextField,lastNameLengthLabel,UserConstants.LengthUserLastName,UserConstants.USER_LAST_NAME,UserConstants.TextLengthLastName);
        Check.checkLength(firstNameTextField,firstNameLengthLabel,UserConstants.LengthUserFirstName,UserConstants.USER_FIRST_NAME,UserConstants.TextLengthFirstName);

    }

    private void disable(boolean bool){
        lastNameTextField.setDisable(bool);
        firstNameTextField.setDisable(true);
        loginTextField.setVisible(false);
        passwordPasswordField.setVisible(false);
        actionButton.setDisable(true);
        radioButtonGridPane.setDisable(true);
    }

    private void lengthReset(){
        firstNameLengthLabel.setText(null);
        lastNameLengthLabel.setText(null);
        loginLengthLabel.setText(null);
        passwordLengthLabel.setText(null);
    }
    private void reset(){
        setData(null,null,null);
        lengthReset();

    }

    private void tableViewNull(boolean bol){
        tableView.setVisible(bol);
        editRadioButton.setVisible(bol);
        deleteRadioButton.setVisible(bol);
        tableViewNullLabel.setVisible(!bol);
        tableViewNullLabel.setText(BookConstants.TableViewNull);
    }

    private void restartTableView() {
        try {
            userData = new UserSql().observableList();
            if (userData.isEmpty()){
                tableViewNull(false);
            } else {
                tableViewNull(true);
                tableView.setItems(userData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserTextField() {
        return new User(
                        new Coder(loginTextField.getText()).getHash(),
                        new Coder(passwordPasswordField.getText()).getHash(),
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        typeRadioButtonInInt());
    }

    @FXML
    void initialize() {
        disable(true);
        addRadioButton.setToggleGroup(actionGroup);
        addRadioButton.setText(UserConstants.TextAdd);
        editRadioButton.setToggleGroup(actionGroup);
        editRadioButton.setText(UserConstants.TextEdit);
        deleteRadioButton.setToggleGroup(actionGroup);
        deleteRadioButton.setText(UserConstants.TextDelete);

        adminRadioButton.setToggleGroup(typeGroup);
        adminRadioButton.setText(UserConstants.TextAdmin);
        pastorRadioButton.setToggleGroup(typeGroup);
        pastorRadioButton.setText(UserConstants.TextPastor);
        librarianRadioButton.setToggleGroup(typeGroup);
        librarianRadioButton.setText(UserConstants.TextLibrarian);


        cancelButton.setText(UserConstants.TextCancel);
        actionButton.setText(UserConstants.TextOk);
        lastNameTextField.setPromptText(UserConstants.USER_LAST_NAME);
        firstNameTextField.setPromptText(UserConstants.USER_FIRST_NAME);
        loginTextField.setPromptText(UserConstants.USER_LOGIN);
        passwordPasswordField.setPromptText(UserConstants.USER_PASSWORD);


        lastNameTableColumn.setText(UserConstants.USER_LAST_NAME);
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<UserT,String>(UserConstants.USER_LastName));
        firstNameTableColumn.setText(UserConstants.USER_FIRST_NAME);
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<UserT,String>(UserConstants.USER_FirstName));
        typeTableColumn.setText(UserConstants.USER_TYPE);
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<UserT,Integer>(UserConstants.LEVEL));
        restartTableView();
    }
}

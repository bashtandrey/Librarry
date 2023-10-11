package com.bashtan.librarry.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;


import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.Check;
import com.bashtan.librarry.application.Message;
import com.bashtan.librarry.constructor.Gender;
import com.bashtan.librarry.constructor.Human;
import com.bashtan.librarry.constructor.HumanT;
import com.bashtan.librarry.sql.HumanSql;
import com.bashtan.librarry.сonstants.HumanConstants;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HumanTableController {

    private ObservableList<HumanT> humanData;
    private  ToggleGroup genderGroup = new ToggleGroup();
    private  ToggleGroup actionGroup = new ToggleGroup();
    private int id =0;
    private boolean flagHuman;
    private boolean selectFlag;
    private static String errorTextMessageTitle;

    private Gender gender = Gender.NULL;


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane genderGridPane;

    @FXML
    private RadioButton addRadioButton;
    @FXML
    private RadioButton deleteRadioButton;
    @FXML
    private RadioButton editRadioButton;

    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private TableView<HumanT> tableView = new TableView<HumanT>();
    @FXML
    private TableColumn lastNameTableColumn = new TableColumn();
    @FXML
    private TableColumn firstNameTableColumn = new TableColumn();
    @FXML
    private TableColumn birthdayTableColumn = new TableColumn();
    @FXML
    private TableColumn emailTableColumn = new TableColumn();
    @FXML
    private TableColumn phoneTableColumn = new TableColumn();
    @FXML
    private TableColumn genderTableColumn = new TableColumn();
    @FXML
    private TableColumn flagHumanTableColumn = new TableColumn();

    @FXML
    private Button actionButton;
    @FXML
    private Button cancelButton;


    @FXML
    private TextField emailTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;

    @FXML
    private Label lastNameLengthLabel;
    @FXML
    private Label firstNameLengthLabel;
    @FXML
    private Label emailLengthLabel;
    @FXML
    private Label phoneLengthLabel;
    @FXML
    private Label tableViewNullLabel;


    @FXML
    void onMouseClickedFemaleRadioButton(ActionEvent event) {
        System.out.println("w");
    }
    @FXML
    void onMouseClickedMaleRadioButton(ActionEvent event) {
        System.out.println("q");
    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {select();}

    @FXML
    void tableViewOnKeyPressed(KeyEvent event) {select();}

    @FXML
    void actionCancelButton(ActionEvent event) {new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");}

    @FXML
    void onActionButton(ActionEvent event) {
        Object command = actionGroup.getSelectedToggle();
        if (command == addRadioButton){
            new HumanSql().addDB(getHumanTextField());
            restartTableView();
            disable(true);
            reset();
            actionGroupFalse();
        } else if (command == editRadioButton){
            new HumanSql().editDB(id,getHumanTextField());
            restartTableView();
            disable(true);
            reset();
        } else if (command == deleteRadioButton){
            new HumanSql().deleteDB(id);
            restartTableView();
            disable(true);
            reset();
        } else Message.alertInformation(HumanConstants.ActionTitle,HumanConstants.ActionGroup);
    }

    @FXML
    void onActionAddRadioButton(ActionEvent event) {
        id=0;
        reset();
        disable(false);
        actionButton.setText(HumanConstants.TextAdd);
        lastNameTextField.requestFocus();
    }

    @FXML
    void onActionEditRadioButton(ActionEvent event) {
        if(id!=0){
            disable(false);
            actionButton.setText(HumanConstants.TextEdit);
            lastNameTextField.requestFocus();
        }
        else{
            reset();
            actionGroupFalse();
            disable(true);
            Message.alertError(HumanConstants.TextEdit,HumanConstants.TextIdIsEmpty);
        }
    }

    @FXML
    void onActionDeleteRadioButton(ActionEvent event) {
        if(id!=0 & flagHuman==false)
        {
            actionButton.setText(HumanConstants.TextDelete);
            disable(true);
            actionButton.setDisable(false);
        } else {
            actionGroupFalse();
            disable(true);
            if (flagHuman) Message.alertError(HumanConstants.TextDelete,HumanConstants.TextFlagHumanDelete);
            else Message.alertError(HumanConstants.TextDelete,HumanConstants.TextIdIsEmpty);
        }
    }

    @FXML
    void onActionMaleRadioButton(ActionEvent event){
        gender = Gender.MALE;
        actionButton.setDisable(false);
    }

    @FXML
    void onActionFemaleRadioButton(ActionEvent event){
        gender = Gender.FEMALE;
        actionButton.setDisable(false);
    }

    @FXML
    void onActionLastNameTextField(ActionEvent event) {
        Check.check(lastNameTextField,firstNameTextField,HumanConstants.LastNameLabel,HumanConstants.TextIsEmpty);
    }

    @FXML
    void onActonFirstNameTextField(ActionEvent event) {
        Check.check(firstNameTextField,birthdayDatePicker,HumanConstants.FirstNameLabel,HumanConstants.TextIsEmpty);
        selectFlag = true;
    }

    @FXML
    void onActonBirthdayDatePicker(ActionEvent event){
        if(selectFlag) {
            emailTextField.setDisable(false);
            emailTextField.requestFocus();
        }
       }

    @FXML
    void onActionEmailTextField(ActionEvent event) {
        Check.check(emailTextField,phoneTextField,HumanConstants.EmailLabel,HumanConstants.TextIsEmpty);
    }

    @FXML
    void onActonPhoneTextField(ActionEvent event) {
        Check.check(phoneTextField, genderGridPane,HumanConstants.PhoneLabel,HumanConstants.TextIsEmpty);
    }

    @FXML
    void onKeyPressedLastNameTextField(KeyEvent event) {
        errorTextMessageTitle = HumanConstants.LastNameLabel;
        Check.checkLength(lastNameTextField,lastNameLengthLabel,HumanConstants.LengthLastName,errorTextMessageTitle,HumanConstants.TextLengthLastName);
        Check.checkCharacter(lastNameTextField,lettersFilter);
    }

    @FXML
    void onKeyPressedFirstNameTextField(KeyEvent event) {
        errorTextMessageTitle = HumanConstants.FirstNameLabel;
        Check.checkLength(firstNameTextField,firstNameLengthLabel,HumanConstants.LengthFirstName,errorTextMessageTitle,HumanConstants.TextLengthFirstName);
        Check.checkCharacter(firstNameTextField,lettersFilter);
    }

    @FXML
    void onKeyPressedEmailTextField(KeyEvent event) {
        Check.checkLength(emailTextField,emailLengthLabel,HumanConstants.LengthEmail,HumanConstants.EmailLabel,HumanConstants.TextLengthEmail);
    }

    @FXML
    void onKeyPressedPhoneTextField(KeyEvent event) {
        errorTextMessageTitle = HumanConstants.PhoneLabel;
        Check.checkLength(phoneTextField,phoneLengthLabel,HumanConstants.LengthPhone,errorTextMessageTitle,HumanConstants.TextLengthPhone);
        Check.checkCharacter(phoneTextField,integerFilter);
    }


    private void select() {
        HumanT humanT = tableView.getSelectionModel().getSelectedItem();
        selectFlag = false;
        setData(humanT.getLastName(), humanT.getFirstName(), humanT.getBirthday(), humanT.getEmail(), humanT.getPhone(), humanT.getGender());
        id = humanT.getId();
        flagHuman = humanT.getFlagHuman();
        disable(true);
        actionGroupFalse();
        Check.checkLength(lastNameTextField, lastNameLengthLabel, HumanConstants.LengthLastName, HumanConstants.LastNameLabel, HumanConstants.TextLengthLastName);
        Check.checkLength(firstNameTextField, firstNameLengthLabel, HumanConstants.LengthFirstName, errorTextMessageTitle, HumanConstants.TextLengthFirstName);
        Check.checkLength(emailTextField, emailLengthLabel, HumanConstants.LengthEmail, HumanConstants.EmailLabel, HumanConstants.TextLengthEmail);
        Check.checkLength(phoneTextField, phoneLengthLabel, HumanConstants.LengthPhone, errorTextMessageTitle, HumanConstants.TextLengthPhone);
    }
    private void disable(boolean bool){
        lastNameTextField.setDisable(bool);
        firstNameTextField.setDisable(true);
        birthdayDatePicker.setDisable(true);
        emailTextField.setDisable(true);
        phoneTextField.setDisable(true);
        birthdayDatePicker.setDisable(true);
        genderGridPane.setDisable(true);
        actionButton.setDisable(true);
        selectFlag = false;
    }
    private void tableViewNull(boolean bol){
        tableView.setVisible(bol);
        editRadioButton.setVisible(bol);
        deleteRadioButton.setVisible(bol);
        tableViewNullLabel.setVisible(!bol);
        tableViewNullLabel.setText(HumanConstants.TableViewNull);
    }

    private void restartTableView() {
        try {
            humanData = new HumanSql().observableList();
            if (humanData.isEmpty()){
                tableViewNull(false);
            } else {
                tableViewNull(true);
                tableView.setItems(humanData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setData(String lastName, String firstName, LocalDate birthday, String email, String phone, String genderString){
        lastNameTextField.setText(lastName);
        firstNameTextField.setText(firstName);
        emailTextField.setText(email);
        phoneTextField.setText(phone);
        birthdayDatePicker.setValue(birthday);
        switch (genderString) {
            case "FEMALE": {
                genderGroup.selectToggle(femaleRadioButton);
                gender = Gender.FEMALE;
                break;
            }
            case "MALE": {
                genderGroup.selectToggle(maleRadioButton);
                gender = Gender.MALE;
                break;
            }
            case "NULL": {
                genderGroupFalse();
                gender = Gender.NULL;
                break;
            }
        }
    }
    private void reset(){
        setData(null,null, null,null,null,"NULL");
        lastNameLengthLabel.setText(null);
        firstNameLengthLabel.setText(null);
        emailLengthLabel.setText(null);
        phoneLengthLabel.setText(null);
    }

    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9]*")) {
            return change;
        }
        Message.alertInformation(errorTextMessageTitle,HumanConstants.TextIsNumber);
        return null;
    };

    UnaryOperator<TextFormatter.Change> lettersFilter = change -> {
    String input = change.getText();
    if (input.matches("[A-Z]*[a-z]*")) {
        return change;
    }
    Message.alertInformation(errorTextMessageTitle, HumanConstants.TextIsLetters);
    return null;
};

    private Human getHumanTextField(){

        return new Human(
                lastNameTextField.getText(),
                firstNameTextField.getText(),
                birthdayDatePicker.getValue(),
                emailTextField.getText(),
                phoneTextField.getText(),
                gender);
    }
    private void actionGroupFalse(){if (!(actionGroup.getSelectedToggle() == null))actionGroup.getSelectedToggle().setSelected(false);}
    private void genderGroupFalse(){if (!(genderGroup.getSelectedToggle() == null))genderGroup.getSelectedToggle().setSelected(false);}



    @FXML
    void initialize() {
        disable(true);

        maleRadioButton.setToggleGroup(genderGroup);
        femaleRadioButton.setToggleGroup(genderGroup);

        addRadioButton.setToggleGroup(actionGroup);
        editRadioButton.setToggleGroup(actionGroup);
        deleteRadioButton.setToggleGroup(actionGroup);

        cancelButton.setText(HumanConstants.CancelButton);
        actionButton.setText(HumanConstants.OkButton);

        femaleRadioButton.setText(HumanConstants.FemaleRadioButton);
        maleRadioButton.setText(HumanConstants.MaleCancelButton);

        addRadioButton.setText(HumanConstants.TextAdd);
        editRadioButton.setText(HumanConstants.TextEdit);
        deleteRadioButton.setText(HumanConstants.TextDelete);

        lastNameTextField.setPromptText(HumanConstants.LastNameLabel);
        firstNameTextField.setPromptText(HumanConstants.FirstNameLabel);
        emailTextField.setPromptText(HumanConstants.EmailLabel);
        phoneTextField.setPromptText(HumanConstants.PhoneLabel);
        birthdayDatePicker.setPromptText(HumanConstants.BirthdayLabel);

        lastNameTableColumn.setText(HumanConstants.LastNameLabel);
        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.LAST_NAME));
        firstNameTableColumn.setText(HumanConstants.FirstNameLabel);
        firstNameTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.FIRST_NAME));
        birthdayTableColumn.setText(HumanConstants.BirthdayLabel);
        birthdayTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT, Date>(HumanConstants.BIRTHDAY));
        emailTableColumn.setText(HumanConstants.EmailLabel);
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.EMAIL));
        phoneTableColumn.setText(HumanConstants.PhoneLabel);
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT,String>(HumanConstants.PHONE));
        genderTableColumn.setText(HumanConstants.GenderLabel);
        genderTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT, Gender>(HumanConstants.GENDER));
        flagHumanTableColumn.setText(HumanConstants.flagHumanCollLabel);
        flagHumanTableColumn.setCellValueFactory(new PropertyValueFactory<HumanT, Boolean>(HumanConstants.FLAG_HUMAN));
        restartTableView();
    }
}
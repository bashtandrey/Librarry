package com.bashtan.librarry.controller;

import com.bashtan.librarry.application.*;
import com.bashtan.librarry.constructor.User;
import com.bashtan.librarry.sql.ConnectDBSql;
import com.bashtan.librarry.sql.CreateDBSql;
import com.bashtan.librarry.сonstants.SettingsConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.security.NoSuchAlgorithmException;

public class SettingsController {

    ToggleGroup group = new ToggleGroup();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label titleLabel;

    @FXML
    private GridPane action1GridPane;


    @FXML
    private  Label hostLabel;
    @FXML
    private TextField hostTextField;

    @FXML
    private  Label loginBDLabel;
    @FXML
    private TextField loginBDTextField;

    @FXML
    private  Label passwordBDLabel;
    @FXML
    private PasswordField passwordBDPasswordField;

    @FXML
    private  Label adminLoginLabel;
    @FXML
    private TextField adminLoginTextField;

    @FXML
    private  Label adminPasswordLabel;
    @FXML
    private PasswordField adminPasswordPasswordField;

    @FXML
    private GridPane action2GridPane;

    @FXML
    private  Label hostBDLabel;
    @FXML
    private CheckBox deleteCheckBox;

    @FXML
    private RadioButton loadRadioButton;
    @FXML
    private RadioButton createRadioButton;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @FXML
    void okButtonOnAction(ActionEvent event) throws Exception {
        if (Start.FLAG_FILE) flagDelete();
        else flagLoad();
    }

    @FXML
    void cancelButtonOnActon(ActionEvent event) {
        new Back().back(anchorPane, "/com/bashtan/librarry/authorization.fxml");
    }

    @FXML
    void onActionLoadRadioButton(ActionEvent event) {
        okButton.setText(SettingsConstants.LOAD_BUTTON);
        okButton.setDisable(true);
        loadVisible(true);
        createVisible(false);

    }
    @FXML
    void onActionCreateRadioButton(ActionEvent event) {
        loadVisible(true);
        createVisible(true);
        okButton.setText(SettingsConstants.CREATE_BUTTON);
        okButton.setDisable(true);

    }
    private void createVisible(boolean bol) {
        adminLoginLabel.setVisible(bol);
        adminLoginTextField.setVisible(bol);
        adminPasswordLabel.setVisible(bol);
        adminPasswordPasswordField.setVisible(bol);
        adminLoginTextField.setDisable(bol);
        adminPasswordPasswordField.setDisable(bol);
    }
    private void loadVisible(boolean bol) {
        hostLabel.setVisible(bol);
        loginBDLabel.setVisible(bol);
        passwordBDLabel.setVisible(bol);
        hostTextField.setVisible(bol);
        loginBDTextField.setVisible(bol);
        passwordBDPasswordField.setVisible(bol);
        loginBDTextField.setDisable(bol);
        passwordBDPasswordField.setDisable(bol);

    }

    @FXML
    void onActionHostTextField(ActionEvent event) {
        Check.check(hostTextField,loginBDTextField,SettingsConstants.HostLabel,SettingsConstants.TEXT_ISEMPTY);
    }
    @FXML
    void onActionLoginBDTextField(ActionEvent event) {
     Check.check(loginBDTextField,passwordBDPasswordField,SettingsConstants.LoginBDLabel,SettingsConstants.TEXT_ISEMPTY);
    }
    @FXML
    void onActionPasswordBDPasswordField(ActionEvent event) {
        Object object = group.getSelectedToggle();
        if (object == loadRadioButton)Check.check(passwordBDPasswordField,okButton,SettingsConstants.PasswordBDLabel,SettingsConstants.TEXT_ISEMPTY);
        else Check.check(passwordBDPasswordField,adminLoginTextField,SettingsConstants.PasswordBDLabel,SettingsConstants.TEXT_ISEMPTY);
    }
    @FXML
    void onActionAdminLoginTextField(ActionEvent event) {
        Check.check(adminLoginTextField, adminPasswordPasswordField,SettingsConstants.AdminLoginLabel,SettingsConstants.TEXT_ISEMPTY);
    }
    @FXML
    void onActionAdminPasswordBDPasswordField(ActionEvent event) {
        Check.check(adminPasswordPasswordField,okButton,SettingsConstants.AdminPasswordLabel,SettingsConstants.TEXT_ISEMPTY);
    }

    private void flagDelete(){
        if (deleteCheckBox.isSelected()){
            Start.fileDelete();
            Start.start();
            new Back().back(anchorPane, "/com/bashtan/librarry/authorization.fxml");
        } else Message.alertError(SettingsConstants.DELETE_BUTTON,SettingsConstants.TEXT_INFORMATION_Delete);
    }


    private void flagLoad() throws NoSuchAlgorithmException {
        String saveFile = hostTextField.getText() + "%" + loginBDTextField.getText() + "%" + passwordBDPasswordField.getText();
       if(group.getSelectedToggle()==createRadioButton){
           Start.fileCreate(saveFile);
           Start.start();
           Start.fileRead();
           CreateDBSql.create(new User(new Coder(adminLoginTextField.getText()).getHash(), new Coder(adminPasswordPasswordField.getText()).getHash(),"","",0));
           new Back().back(anchorPane, "/com/bashtan/librarry/authorization.fxml");
       } else if(group.getSelectedToggle()==loadRadioButton){
           Start.fileCreate(saveFile);
           Start.start();
           Start.fileRead();
           ConnectDBSql.connect();
           new Back().back(anchorPane, "/com/bashtan/librarry/authorization.fxml");
       }
    }

    @FXML
    void initialize() {
        createVisible(false);
        loadVisible(false);
        createRadioButton.setToggleGroup(group);
        loadRadioButton.setToggleGroup(group);

        hostTextField.setText("localhost:3306");
        loginBDTextField.setText("root");
        passwordBDPasswordField.setText("Vova1702");
        adminLoginTextField.setText("Andrii");
        adminPasswordPasswordField.setText("Vova1702");

        loadRadioButton.setText(SettingsConstants.LOAD_RADIOBUTTON);
        createRadioButton.setText(SettingsConstants.CREATE_RADIOBUTTON);

        deleteCheckBox.setText(SettingsConstants.CheckBox);
        titleLabel.setText(SettingsConstants.TITLE);
        cancelButton.setText(SettingsConstants.CANCEL_BUTTON);

        hostLabel.setText(SettingsConstants.HostLabel);
        loginBDLabel.setText(SettingsConstants.LoginBDLabel);
        passwordBDLabel.setText(SettingsConstants.PasswordBDLabel);
        adminLoginLabel.setText(SettingsConstants.AdminLoginLabel);
        adminPasswordLabel.setText(SettingsConstants.AdminPasswordLabel);
        hostBDLabel.setText(Start.HOST);

        if (Start.FLAG_FILE){
            action2GridPane.setVisible(true);
            action1GridPane.setVisible(false);
            okButton.setText(SettingsConstants.DELETE_BUTTON);

        } else {
            action1GridPane.setVisible(true);
            action2GridPane.setVisible(false);
            okButton.setText(SettingsConstants.OK_BUTTON);
            okButton.setDisable(true);
        }
    }

}

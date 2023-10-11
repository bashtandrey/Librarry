package com.bashtan.librarry.controller;


import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.Coder;
import com.bashtan.librarry.application.Message;
import com.bashtan.librarry.application.Start;
import com.bashtan.librarry.sql.ConnectDBSql;
import com.bashtan.librarry.sql.UserSql;
import com.bashtan.librarry.сonstants.AuthorizationConstants;
import com.bashtan.librarry.сonstants.ConnectionDBConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;


public class AuthorizationController {
    private static String hashPassword;
    private static String hashLogin;

    @FXML
    private Button CancelButton;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button authorizationButton;

    @FXML
    private Label connectionLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button settingsButton;

    @FXML
    private Label titleLabel;

    @FXML
    void authorizationButtonOnAction(ActionEvent event) {

        if (loginTextField.getText().isEmpty()){
            Message.alertError(AuthorizationConstants.LOGIN_LABEL, AuthorizationConstants.IS_EMPTY);
        } else if (passwordTextField.getText().isEmpty()){
            Message.alertError(AuthorizationConstants.PASSWORD_LABEL, AuthorizationConstants.IS_EMPTY);
        } else {
                hashLogin = new Coder(loginTextField.getText()).getHash();
                hashPassword = new Coder(passwordTextField.getText()).getHash();
            try {

                if (new UserSql(hashLogin,hashPassword).authorization()) {
                    new Back().back(anchorPane, "/com/bashtan/librarry/main.fxml");
                } else Message.alertError(AuthorizationConstants.LOG_IN,AuthorizationConstants.NOT_CORRECT);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
    System.exit(0);
    }

    @FXML
    void settingsButtonOnAction(ActionEvent event) {
        new Back().back(anchorPane, "/com/bashtan/librarry/settings.fxml");
    }

    public void onActionLoginTextField(ActionEvent event) {passwordTextField.requestFocus();}

    public void onActionPasswordTextField(ActionEvent event) {authorizationButton.requestFocus();
    }



    @FXML
    void initialize()  {
        authorizationButton.setDisable(true);
        loginTextField.setDisable(true);
        passwordTextField.setDisable(true);
        titleLabel.setText(AuthorizationConstants.TITLE_LABEL);
        loginLabel.setText(AuthorizationConstants.LOGIN_LABEL);
        loginTextField.setPromptText(AuthorizationConstants.LOGIN_LABEL);
        passwordLabel.setText(AuthorizationConstants.PASSWORD_LABEL);
        passwordTextField.setPromptText(AuthorizationConstants.PASSWORD_LABEL);
        settingsButton.setText(AuthorizationConstants.SETTINGS_BUTTON);
        CancelButton.setText(AuthorizationConstants.CANCEL_BUTTON);
        authorizationButton.setText(AuthorizationConstants.AUTHORIZATION_BUTTON);
        loginTextField.requestFocus();

        loginTextField.setText("Milana");
        passwordTextField.setText("Milana1309");

        if (Start.FLAG_FILE) {
            checkConnect();
        } else connectionLabel.setText(AuthorizationConstants.FILE_ERROR);

    }

    void checkConnect(){

        if (new ConnectDBSql().getStatusConnect()) {
            connectionLabel.setText(ConnectionDBConstants.CONNECTION_TRUE);
            authorizationButton.setDisable(false);
            loginTextField.setDisable(false);
            passwordTextField.setDisable(false);
            settingsButton.setVisible(false);
        }
        else {
            connectionLabel.setText(ConnectionDBConstants.CONNECTION_FALSE);
        }
    }
}

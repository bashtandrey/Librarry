package com.bashtan.librarry.controller;


import java.io.IOException;

import com.bashtan.chat.server.ChatServer;
import com.bashtan.librarry.application.Back;
import com.bashtan.librarry.application.StartApplication;
import com.bashtan.librarry.sql.UserSql;
import com.bashtan.librarry.сonstants.MainConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Menu libraryMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private Menu exitMenu;

    @FXML
    private Menu userMenu;

    @FXML
    private Menu serverMenu;
    @FXML
    private Menu settingMenu;

    @FXML
    private MenuItem bookMenuItem;
    @FXML
    private MenuItem humanMenuItem;

    @FXML
    private MenuItem giveBookMenuItem;

    @FXML
    private MenuItem getBookMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem userMenuItem;


    @FXML
    private Label titleHumanLabel;
    @FXML
    private Label humanFlagLabel;
    @FXML
    private Label humanFlagLengthLabel;
    @FXML
    private Label humanLabel;
    @FXML
    private Label humanLengthLabel;

    @FXML
    private Label titleBookLabel;
    @FXML
    private Label bookLabel;
    @FXML
    private Label bookFlagLabel;
    @FXML
    private Label bookLengthLabel;
    @FXML
    private Label bookFlagLengthLabel;


    @FXML
    void onActionBookMenuItem(ActionEvent event) throws IOException {
        new Back().back(anchorpane, "/com/bashtan/librarry/bookTable.fxml");
    }

    @FXML
    void onActionHumanMenuItem(ActionEvent event) throws IOException {
        new Back().back(anchorpane, "/com/bashtan/librarry/humanTable.fxml");
    }

    @FXML
    void onActionGiveBookMenuItem(ActionEvent event) throws IOException {
        new Back().back(anchorpane, "/com/bashtan/librarry/giveBookTable.fxml");
    }

    @FXML
    void onActionGetBookMenuItem(ActionEvent event) throws IOException {
        new Back().back(anchorpane, "/com/bashtan/librarry/getBookTable.fxml");
    }

    @FXML
    void onActionExitMenuItem(ActionEvent event) throws IOException {
        StartApplication.closeAPP();
    }

    @FXML
    void onActionUserMenuItem(ActionEvent event) throws IOException {
        new Back().back(anchorpane, "/com/bashtan/librarry/userTable.fxml");
    }

    @FXML
    void onActionStartServerMenuItem(ActionEvent event) throws IOException {

    }
    @FXML
    void onActionStopServerMenuItem(ActionEvent event) throws IOException {
    }




    private void nullType() {
        settingMenu.setVisible(false);
        serverMenu.setVisible(false);
        libraryMenu.setVisible(false);
    }

    private void userType() {
        int level = UserSql.getUser().getLevel();
        switch (level) {
            case 0: {
                // NULL
//                userMenu.setDisable(true);
            }
            case 1: {
//                userMenu.setText("Admin");
//                userMenu.setDisable(false);
                break;
            }
            case 2: {
                //Pastor
//                userMenu.setDisable(true);
            }
            // case 3 = Librarian
            case 3: {
                userMenu.setDisable(true);
                userMenu.setText(MainConstants.LIBRARIAN);
                libraryMenu.setVisible(true);

            }
            case 4: {
                // Bebi
            }
        }

    }

    private Thread startChatServer() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                new ChatServer();}
        });
    }


    @FXML
        void initialize() {
            nullType();
            userType();
            libraryMenu.setText(MainConstants.LIBRARY_MENU);
            helpMenu.setText(MainConstants.HELP_MENU);
            exitMenu.setText(MainConstants.EXIT);
            exitMenuItem.setText(MainConstants.EXIT);
            bookMenuItem.setText(MainConstants.BOOK_MENU_ITEM);
            humanMenuItem.setText(MainConstants.HUMAN_MENU_ITEM);
            userMenuItem.setText(MainConstants.USER_MENU_ITEM);
            giveBookMenuItem.setText(MainConstants.GIVE_BOOK_MENU_ITEM);
            getBookMenuItem.setText(MainConstants.GET_BOOK_MENU_ITEM);

            titleHumanLabel.setText(MainConstants.HUMAN_TITLE);
            humanLabel.setText(MainConstants.HUMAN_TEXT);
            humanFlagLabel.setText(MainConstants.HUMAN_READER_TEXT);

            titleBookLabel.setText(MainConstants.BOOK_TITLE);
            bookLabel.setText(MainConstants.BOOK_TEXT);
            bookFlagLabel.setText(MainConstants.BOOK_READER_TEXT);

        }
    }

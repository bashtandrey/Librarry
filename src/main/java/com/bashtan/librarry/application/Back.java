package com.bashtan.librarry.application;

import com.bashtan.librarry.сonstants.APPConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Back {

    public void back(AnchorPane anchorPane, String resource) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Scene scene = stage.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
        Parent parent = null;
        try {
            parent = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(parent);
        stage.setScene(scene);
    }

    public Stage newStage(String resource){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(resource));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(APPConstants.TITLE_LABEL);
        stage.setScene(scene);
        stage.getIcons().add(new Image(APPConstants.FILE_ICON));
        stage.show();
        return stage;
    }
}

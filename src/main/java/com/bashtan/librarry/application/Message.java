package com.bashtan.librarry.application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import static javafx.scene.control.ButtonType.*;

public class Message {

    public static void alertError(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(null);
        alert.showAndWait();
    }
    public static void alertInformation(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(null);
        alert.showAndWait();
    }

    public static boolean alertClose(String title,String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.setContentText(null);
        if (alert.showAndWait().filter(t -> t == OK).isPresent()) {
            return true;
        }else return false;
        }

        public static boolean alertConfirmation(String title,String headerText,String contextText){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contextText);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get()==ButtonType.OK) return true;
            else return false;
        }


}

package com.bashtan.librarry.application;

import com.bashtan.librarry.sql.BookSql;
import com.bashtan.librarry.сonstants.BookConstants;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

public class Check {

    public static void checkLength(TextArea textArea, Label label, int lengthLabel, String title, String textLength) {
        int length = textArea.getLength();
        if(length<=lengthLabel)
        {
            label.setText(String.valueOf(length));
            label.setTextFill(Color.BLACK);
        } else {
            label.setText(String.valueOf(length));
            label.setTextFill(Color.RED);
            Message.alertInformation(title,textLength);
        }
    }

    public static void checkLength(TextField textField, Label label, int lengthLabel, String title, String textLength) {
        int length = textField.getLength();
        if(length<=lengthLabel)
        {
            label.setText(String.valueOf(length));
            label.setTextFill(Color.BLACK);
        } else {
            label.setText(String.valueOf(length));
            label.setTextFill(Color.RED);
            Message.alertInformation(title,textLength);
        }
    }


    public static void check(boolean boll,TextField textFieldFirst, TextArea textArea, Button button, String title, String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title, textMessage);
        else if(BookSql.checkSearchSerialDB(boll,textFieldFirst.getText())) {
                textArea.setDisable(false);
                textArea.requestFocus();
                button.setDisable(false);

            } else Message.alertInformation(title, BookConstants.TextSerialFalse);
    }
    public static void check(TextField textFieldFirst,TextField textFieldSecond,String title,String textMessage) {
            if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
        Message.alertInformation(title, textMessage);
        else {
            textFieldSecond.setDisable(false);
            textFieldSecond.requestFocus();
        }
    }
    public static void check(TextField textFieldFirst,Button textFieldSecond,String title,String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title, textMessage);
        else {
            textFieldSecond.setDisable(false);
            textFieldSecond.requestFocus();
        }
    }

    public static void check(TextField textFieldFirst, DatePicker datePicker, String title, String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title,textMessage);
        else {
            datePicker.setDisable(false);
            datePicker.requestFocus();
        }
    }
    public static void check(TextField textFieldFirst, GridPane gridPane, String title, String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title,textMessage);
        else {
            gridPane.setDisable(false);
            gridPane.requestFocus();
        }
    }

    public static void checkUser(TextField textFieldFirst,TextField textFieldSecond,String title,String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title, textMessage);
        else {
            textFieldSecond.setVisible(true);
            textFieldSecond.setDisable(false);
            textFieldSecond.requestFocus();
        }
    }
    public static void checkUser(TextField textFieldFirst,GridPane gridPane,String title,String textMessage) {
        if (textFieldFirst.getText() == null || textFieldFirst.getText().isEmpty())
            Message.alertInformation(title, textMessage);
        else {
            gridPane.setDisable(false);
        }
    }

    public static void checkCharacter(TextField textField, UnaryOperator unaryOperator){
             textField.setTextFormatter(new TextFormatter<>(unaryOperator));
    }

}

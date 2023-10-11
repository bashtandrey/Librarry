package com.bashtan.librarry.application;

import com.bashtan.librarry.sql.ConnectDBSql;
import com.bashtan.librarry.сonstants.APPConstants;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("/com/bashtan/librarry/authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(APPConstants.TITLE_LABEL);
        stage.setScene(scene);
        stage.getIcons().add(new Image(APPConstants.FILE_ICON));
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                if (Message.alertClose(APPConstants.TITLE_EXIT,APPConstants.TEXT_EXIT)){
                closeAPP();
                }

            }
        });



    }
    public static void closeAPP(){
        if (ConnectDBSql.getConnection()==null)
        {
            System.exit(0);
        } else {
            ConnectDBSql.closeConnection();
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        Start.start();
        if (Start.FLAG_FILE){
            Start.fileRead();
            ConnectDBSql.connect();
            launch();
        } else {
            launch();
        }
    }
}
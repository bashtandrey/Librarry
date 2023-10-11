module com.bashtan.rol {

    requires org.controlsfx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;

    opens com.bashtan.librarry to javafx.fxml;
    exports com.bashtan.librarry;

    opens com.bashtan.librarry.constructor to javafx.fxml;
    exports com.bashtan.librarry.constructor;

    exports com.bashtan.librarry.controller;
    opens com.bashtan.librarry.controller to javafx.fxml;

    exports com.bashtan.librarry.application;
    opens com.bashtan.librarry.application to javafx.fxml;

    opens  com.bashtan.chat.client to javafx.fxml;
    exports com.bashtan.chat.client;


}
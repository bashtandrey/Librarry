package com.bashtan.librarry.sql;

import com.bashtan.librarry.application.Start;
import com.bashtan.librarry.constructor.User;
import com.bashtan.librarry.сonstants.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDBSql {
    private static Connection connectionCreate;
    private final static String URL = "jdbc:mysql://" + Start.HOST;
    private final static String USER = Start.LOGIN_BD;
    private final static String PASSWORD = Start.PASSWORD_BD;
    private static boolean[] create = new boolean[12];

    private static boolean connectCreate(){
       try {
           connectionCreate = DriverManager.getConnection(URL,USER,PASSWORD);
           return true;
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }

   }
//
//    private static void check(boolean bol,String title,String text) {
//        if (!bol){
//            Message.alertError(title,text);
//        }
//    }

    public static void create(User user)  {
        connectCreate();
//        createDB();
//        createTableUser();
//        createTableBook();
//        createTableHuman();
//        createTableGiveBook();
//        createTableHistory();
        createForeignKey();
//        createUniqueKey();
        connectionClose();
//        ConnectDBSql.connect();
//        createAdmin(user);
//        ConnectDBSql.closeConnection();
    }

    private static boolean connectionClose() {
        try {
            connectionCreate.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean createDB(){
        String createDB = "CREATE SCHEMA "+ APPConstants.NAME_BD + " DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;";

        try {
            connectionCreate.createStatement().executeUpdate(createDB);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createTableBook(){
        String book="CREATE TABLE `"+ APPConstants.NAME_BD + "`.`"+ BookConstants.BOOK +"` (\n" +
                "  `" + BookConstants.ID + "` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `" + BookConstants.NAME_BOOK + "` VARCHAR(" + BookConstants.LengthNameBook + ") NOT NULL,\n" +
                "  `" + BookConstants.AUTHOR +"` VARCHAR(" + BookConstants.LengthAuthor + ") NOT NULL,\n" +
                "  `" + BookConstants.PUBLISHING_YEAR +"` INT(" + BookConstants.LengthPublishingYear+") NOT NULL,\n" +
                "  `" + BookConstants.PUBLISHING_HOUSE +"` VARCHAR(" + BookConstants.LengthPublishingHouse + ") NOT NULL,\n" +
                "  `" + BookConstants.DESCRIPTION +"` VARCHAR(" + BookConstants.LengthDescription  +") NULL,\n" +
                "  `" + BookConstants.SERIAL +"` INT(" + BookConstants.LengthSerial +") NOT NULL,\n" +
                "  `" + BookConstants.FlAG_BOOK +"` BIT(1) NOT NULL,\n" +
                "  `" + BookConstants.HUMAN_ID +"` INT NULL,\n" +
                "  PRIMARY KEY (`" + BookConstants.ID + "`));\n";
        try {
            connectionCreate.createStatement().executeUpdate(book);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createTableHuman(){

        String human ="CREATE TABLE `"+ APPConstants.NAME_BD + "`.`"+ HumanConstants.HUMAN +"` (\n" +
                "  `" + HumanConstants.ID + "` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `" + HumanConstants.LAST_NAME + "` VARCHAR(" + HumanConstants.LengthLastName + ") NOT NULL,\n" +
                "  `" + HumanConstants.FIRST_NAME +"` VARCHAR(" + HumanConstants.LengthFirstName + ") NOT NULL,\n" +
                "  `" + HumanConstants.BIRTHDAY +"` DATE NULL,\n" +
                "  `" + HumanConstants.EMAIL +"` VARCHAR(" + HumanConstants.LengthEmail + ") NULL,\n" +
                "  `" + HumanConstants.PHONE +"` VARCHAR(" + HumanConstants.LengthPhone + ") NULL,\n" +
                "  `" + HumanConstants.GENDER +"` VARCHAR(" + HumanConstants.LengthGender +") NOT NULL,\n" +
                "  `" + HumanConstants.FLAG_HUMAN +"` BIT(1) NOT NULL,\n" +
                "  PRIMARY KEY (`" + HumanConstants.ID + "`));\n";
        try {
            connectionCreate.createStatement().executeUpdate(human);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createTableUser(){
        String user ="CREATE TABLE `" + APPConstants.NAME_BD + "`.`" + UserConstants.USER +"` (\n" +
                "  `" + UserConstants.ID + "` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `" + UserConstants.LOGIN + "` VARCHAR(" + UserConstants.LengthLogin + ") NOT NULL,\n" +
                "  `" + UserConstants.PASSWORD +"` VARCHAR(" + UserConstants.LengthPassword + ") NOT NULL,\n" +
                "  `" + UserConstants.USER_LastName +"` VARCHAR(" + UserConstants.LengthUserLastName + ") NULL,\n" +
                "  `" + UserConstants.USER_FirstName +"` VARCHAR(" + UserConstants.LengthUserFirstName + ") NULL,\n" +
                "  `" + UserConstants.LEVEL +"` INT("+ UserConstants.LengthLevel +") NOT NULL,\n" +
                "  PRIMARY KEY (`" + UserConstants.ID + "`));\n";
        try {
            connectionCreate.createStatement().executeUpdate(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createAdmin(User user)  {
            if (new UserSql().addDB(user)) return true;
            else return false;
    }

    private static boolean createTableGiveBook(){
        String book="CREATE TABLE `"+ APPConstants.NAME_BD + "`.`"+ GiveBookTableConstants.GIVE_BOOK +"` (\n" +
                "  `" + GiveBookTableConstants.ID + "` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `" + GiveBookTableConstants.HUMAN_ID + "` INT NOT NULL,\n" +
                "  `" + GiveBookTableConstants.BOOK_ID + "` INT NOT NULL,\n" +
                "  `" + GiveBookTableConstants.USER_ID +"` INT NOT NULL,\n" +
                "  `" + GiveBookTableConstants.DATE_IN +"` DATE NOT NULL,\n" +
                "  PRIMARY KEY (`" + GiveBookTableConstants.ID + "`));\n";
        try {
            connectionCreate.createStatement().executeUpdate(book);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createTableHistory(){
        String book="CREATE TABLE `"+ APPConstants.NAME_BD + "`.`"+ HistoryTableConstants.HISTORY +"` (\n" +
                "  `" + HistoryTableConstants.ID + "` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `" + HistoryTableConstants.HUMAN_ID + "` INT NOT NULL,\n" +
                "  `" + HistoryTableConstants.BOOK_ID + "` INT NOT NULL,\n" +
                "  `" + HistoryTableConstants.USER_ID +"` INT NOT NULL,\n" +
                "  `" + HistoryTableConstants.DATE_IN +"` DATE NOT NULL,\n" +
                "  `" + HistoryTableConstants.DATE_OUT +"` DATE NOT NULL,\n" +
                "  PRIMARY KEY (`" + HistoryTableConstants.ID + "`));\n";
        try {
            connectionCreate.createStatement().executeUpdate(book);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createForeignKey(){
        String [] foreignKey = new String[8];
        foreignKey[0] = "ALTER TABLE " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + " ADD CONSTRAINT " + GiveBookTableConstants.GIVE_BOOK_BOOK_FK + " FOREIGN KEY (" + GiveBookTableConstants.BOOK_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + BookConstants.BOOK + "(" + BookConstants.ID + ");";
        foreignKey[1] = "ALTER TABLE " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + " ADD CONSTRAINT " + GiveBookTableConstants.GIVE_BOOK_USER_FK + " FOREIGN KEY (" + GiveBookTableConstants.USER_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + UserConstants.USER + "(" + UserConstants.ID + ");";
        foreignKey[2] = "ALTER TABLE " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + " ADD CONSTRAINT " + GiveBookTableConstants.GIVE_BOOK_HUMAN_FK + " FOREIGN KEY (" + GiveBookTableConstants.HUMAN_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + HumanConstants.HUMAN + "(" + HumanConstants.ID + ");";
        foreignKey[3] = "ALTER TABLE " + APPConstants.NAME_BD + "." + BookConstants.BOOK + " ADD CONSTRAINT " + BookConstants.BOOK_FK + " FOREIGN KEY (" + BookConstants.HUMAN_ID+") REFERENCES " + APPConstants.NAME_BD + "." + HumanConstants.HUMAN + "(" + HumanConstants.ID + ");";
        foreignKey[4] = "ALTER TABLE " + APPConstants.NAME_BD + "." + HistoryTableConstants.HISTORY + " ADD CONSTRAINT " + HistoryTableConstants.HISTORY_BOOK_FK + " FOREIGN KEY (" + HistoryTableConstants.BOOK_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + "(" + GiveBookTableConstants.BOOK_ID + ");";
        foreignKey[5] = "ALTER TABLE " + APPConstants.NAME_BD + "." + HistoryTableConstants.HISTORY + " ADD CONSTRAINT " + HistoryTableConstants.HISTORY_USER_FK + " FOREIGN KEY (" + HistoryTableConstants.USER_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + "(" + GiveBookTableConstants.USER_ID + ");";
        foreignKey[6] = "ALTER TABLE " + APPConstants.NAME_BD + "." + HistoryTableConstants.HISTORY + " ADD CONSTRAINT " + HistoryTableConstants.HISTORY_HUMAN_FK + " FOREIGN KEY (" + HistoryTableConstants.HUMAN_ID + ") REFERENCES " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + "(" + GiveBookTableConstants.HUMAN_ID + ");";
        foreignKey[7] = "ALTER TABLE " + APPConstants.NAME_BD + "." + HistoryTableConstants.HISTORY + " ADD CONSTRAINT " + HistoryTableConstants.HISTORY_DATE_FK + " FOREIGN KEY (" + HistoryTableConstants.DATE_IN + ") REFERENCES " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + "(" + GiveBookTableConstants.DATE_IN + ");";
        System.out.println(foreignKey[7]);

        try {
//            for (int i = 0; i < foreignKey.length; i++) {
                connectionCreate.createStatement().executeUpdate(foreignKey[7]);
//            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    private static boolean createUniqueKey(){
        String [] uniqueKey = new String[3];
        uniqueKey[0] = "ALTER TABLE " + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + " ADD CONSTRAINT " + GiveBookTableConstants.GIVE_BOOK_UN + " UNIQUE KEY (" + GiveBookTableConstants.BOOK_ID + ");";
        uniqueKey[1] = "ALTER TABLE " + APPConstants.NAME_BD + "." + UserConstants.USER + " ADD CONSTRAINT " + UserConstants.USER_UN + " UNIQUE KEY ("+ UserConstants.LOGIN +");";
        uniqueKey[2] = "ALTER TABLE " + APPConstants.NAME_BD + "." + BookConstants.BOOK + " ADD CONSTRAINT " + BookConstants.BOOK_UN + " UNIQUE KEY (" + BookConstants.SERIAL + ");";
        try {
            for (int i = 0; i < uniqueKey.length; i++) {
                connectionCreate.createStatement().executeUpdate(uniqueKey[i]);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

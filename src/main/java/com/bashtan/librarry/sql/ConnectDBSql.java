package com.bashtan.librarry.sql;


import com.bashtan.librarry.application.Start;
import com.bashtan.librarry.сonstants.APPConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBSql {

    private static  Connection connection = null;
    private static Boolean statusConnect = false;
    private final static String URL = "jdbc:mysql://" + Start.HOST + "/" + APPConstants.NAME_BD;
    private final static String USER = Start.LOGIN_BD;
    private final static String PASSWORD = Start.PASSWORD_BD;

    public static boolean connect(){

        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            statusConnect = true;
            return true;
        } catch (SQLException e) {
            connection = null;
            statusConnect = false;
            return false;
        }
    }

    public static Connection getConnection() {return connection;}

    public static Boolean getStatusConnect() {return statusConnect;}

    public static boolean closeConnection(){
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
}


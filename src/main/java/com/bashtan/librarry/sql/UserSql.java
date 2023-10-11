package com.bashtan.librarry.sql;

import com.bashtan.librarry.constructor.User;
import com.bashtan.librarry.constructor.UserT;
import com.bashtan.librarry.сonstants.UserConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserSql {
    private PreparedStatement preparedStatement = null;
    private static Connection connection = ConnectDBSql.getConnection();
    private ObservableList<UserT> data = FXCollections.observableArrayList();

    private String loginField;
    private String passwordField;
    private static User user;

    public UserSql() {
    }

    public UserSql(String loginField, String passwordField) {
        this.loginField = loginField;
        this.passwordField = passwordField;
    }

    public boolean addDB(User user) {
        String sql = "INSERT INTO "
                + UserConstants.USER +" ("
                + UserConstants.LOGIN +","
                + UserConstants.PASSWORD +","
                + UserConstants.USER_FirstName +","
                + UserConstants.USER_LastName +","
                + UserConstants.LEVEL + ") VALUES((?),(?),(?),(?),(?))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserFirstName());
            preparedStatement.setString(4, user.getUserLastName());
            preparedStatement.setInt(5, user.getLevel());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void editDB(int id, User user) {
        String sql = "UPDATE "
                + UserConstants.USER + " SET "
                + UserConstants.LOGIN + " = ?,"
                + UserConstants.PASSWORD + " = ?,"
                + UserConstants.USER_LastName + " = ?,"
                + UserConstants.USER_FirstName + " = ?,"
                + UserConstants.LEVEL + " = ? " + "WHERE "
                + UserConstants.ID + " =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserLastName());
            preparedStatement.setString(4, user.getUserFirstName());
            preparedStatement.setInt(5, user.getLevel());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(int id) {
        String sql = "DELETE FROM " + UserConstants.USER + " WHERE " + UserConstants.ID + " =(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(){
        return user;
    }


    private static ResultSet searchDB() throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + UserConstants.USER);
    }

    private ResultSet searchAuthorizationDB() throws SQLException {
        String sql = "SELECT * FROM "
                + UserConstants.USER + " WHERE "
                + UserConstants.LOGIN + " = ? and "
                + UserConstants.PASSWORD +" = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginField);
            preparedStatement.setString(2, passwordField);
        return preparedStatement.executeQuery();

    }

    public boolean authorization() throws SQLException {
        ResultSet resultSet = searchAuthorizationDB();
        int index =0;
        while (resultSet.next()){
            user = new User(
                    resultSet.getInt(UserConstants.ID),
                    resultSet.getString(UserConstants.LOGIN),
                    resultSet.getString(UserConstants.PASSWORD),
                    resultSet.getString(UserConstants.USER_FirstName),
                    resultSet.getString(UserConstants.USER_LastName),
                    resultSet.getInt(UserConstants.LEVEL));
            index++;
        }
        if (index==1) return true;
        else return false;
    }

private String typeIntInString(ResultSet resultSet) throws SQLException {
    String type = null;
        switch (resultSet.getInt(UserConstants.LEVEL)) {
        case 0: {
            type = "NULL";
            break;
        }
        case 1: {
            type = "Admin";
            break;
        }
        case 2: {
            type = "Pastor";
            break;
        }
        case 3: {
            type = "Librarian";
            break;
        }
    }
    return type;
}

    public ObservableList observableList() throws SQLException {
        ResultSet resultSet = UserSql.searchDB();
        String type = null;
        while (resultSet.next()) {

            data.add(new UserT(
                    resultSet.getInt(UserConstants.ID),
                    resultSet.getString(UserConstants.LOGIN),
                    resultSet.getString(UserConstants.PASSWORD),
                    resultSet.getString(UserConstants.USER_LastName),
                    resultSet.getString(UserConstants.USER_FirstName),
                    typeIntInString(resultSet)));
        }
        return data;
    }

}
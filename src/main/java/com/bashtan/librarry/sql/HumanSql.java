package com.bashtan.librarry.sql;

import com.bashtan.librarry.constructor.Human;
import com.bashtan.librarry.constructor.HumanT;
import com.bashtan.librarry.сonstants.HumanConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class HumanSql {

    private PreparedStatement preparedStatement = null;
    private final Connection connection = ConnectDBSql.getConnection();
    private ObservableList<HumanT> data = FXCollections.observableArrayList();

    public HumanSql() {}

    public void addDB(Human human) {

        String sql = "INSERT INTO "
        + HumanConstants.HUMAN +" ("
        + HumanConstants.LAST_NAME +","
        + HumanConstants.FIRST_NAME +","
        + HumanConstants.BIRTHDAY +","
        + HumanConstants.EMAIL +","
        + HumanConstants.PHONE +","
        + HumanConstants.GENDER +","
        + HumanConstants.FLAG_HUMAN +") " +
                     "VALUES((?),(?),(?),(?),(?),(?),(?))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, human.getLastName());
            preparedStatement.setString(2, human.getFirstName());
            preparedStatement.setDate(3, Date.valueOf(human.getBirthday()));
            preparedStatement.setString(4, human.getEmail());
            preparedStatement.setString(5, human.getPhone());
            preparedStatement.setString(6, String.valueOf(human.getGender()));
            preparedStatement.setBoolean(7,false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDB(int id) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+ HumanConstants.HUMAN +" WHERE "+ HumanConstants.ID +" =(?)");
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public ResultSet searchDB() throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + HumanConstants.HUMAN);
    }
    public ResultSet searchWitchBookDB() throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + HumanConstants.HUMAN + " WHERE " + HumanConstants.FLAG_HUMAN + " = 1");
    }

    public void editDB(int id, Human human){

        String sql="UPDATE " +
                HumanConstants.HUMAN + " set " +
                HumanConstants.LAST_NAME + " = ?," +
                HumanConstants.FIRST_NAME + " = ?," +
                HumanConstants.BIRTHDAY + "= ?," +
                HumanConstants.EMAIL + " = ?," +
                HumanConstants.PHONE + " = ?," +
                HumanConstants.GENDER + " = ? WHERE " +
                HumanConstants.ID + " = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, human.getLastName());
            preparedStatement.setString(2, human.getFirstName());
            preparedStatement.setDate(3, Date.valueOf(human.getBirthday()));
            preparedStatement.setString(4, human.getEmail());
            preparedStatement.setString(5, human.getPhone());
            preparedStatement.setString(6, String.valueOf(human.getGender()));
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList observableListWithBook() throws SQLException {
        ResultSet resultSet = new HumanSql().searchWitchBookDB();
        while (resultSet.next()) {
            data.add (new HumanT(
                    resultSet.getInt(HumanConstants.ID),
                    resultSet.getString(HumanConstants.LAST_NAME),
                    resultSet.getString(HumanConstants.FIRST_NAME),
                    resultSet.getString(HumanConstants.BIRTHDAY),
                    resultSet.getString(HumanConstants.EMAIL),
                    resultSet.getString(HumanConstants.PHONE),
                    resultSet.getString(HumanConstants.GENDER),
                    resultSet.getBoolean(HumanConstants.FLAG_HUMAN)));
        }
        return data;
    }

    public ObservableList observableList() throws SQLException {
        ResultSet resultSet = new HumanSql().searchDB();
        while (resultSet.next()) {
            data.add (new HumanT(
                    resultSet.getInt(HumanConstants.ID),
                    resultSet.getString(HumanConstants.LAST_NAME),
                    resultSet.getString(HumanConstants.FIRST_NAME),
                    resultSet.getString(HumanConstants.BIRTHDAY),
                    resultSet.getString(HumanConstants.EMAIL),
                    resultSet.getString(HumanConstants.PHONE),
                    resultSet.getString(HumanConstants.GENDER),
                    resultSet.getBoolean(HumanConstants.FLAG_HUMAN)));
        }
        return data;
    }

}
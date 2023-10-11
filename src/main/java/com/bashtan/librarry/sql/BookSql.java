package com.bashtan.librarry.sql;


import com.bashtan.librarry.constructor.Book;
import com.bashtan.librarry.constructor.BookT;
import com.bashtan.librarry.сonstants.BookConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BookSql {
    private PreparedStatement preparedStatement = null;
    private static final Connection connection = ConnectDBSql.getConnection();
    private ObservableList<BookT> data = FXCollections.observableArrayList();

    public void addDB(Book book) {

        String sql = "INSERT INTO "
                + BookConstants.BOOK + " ("
                + BookConstants.NAME_BOOK + ","
                + BookConstants.AUTHOR + ","
                + BookConstants.PUBLISHING_YEAR + ","
                + BookConstants.PUBLISHING_HOUSE + ","
                + BookConstants.DESCRIPTION + ","
                + BookConstants.SERIAL + ","
                + BookConstants.FlAG_BOOK + ") VALUES((?),(?),(?),(?),(?),(?),(?))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getNameBook());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishingYear());
            preparedStatement.setString(4, book.getPublishingHouse());
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setInt(6, book.getSerial());
            preparedStatement.setBoolean(7, false);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteDB(int id) {
        String sql = "DELETE FROM " + BookConstants.BOOK + " WHERE " + BookConstants.ID + " =(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet searchDB() throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + BookConstants.BOOK);
    }
    private static ResultSet searchWitchHumanDB(int id) throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + BookConstants.BOOK + " WHERE " + BookConstants.HUMAN_ID + " = " + id);
    }
    private static ResultSet searchSerialDB(int serial) throws SQLException {
        String sql = "SELECT " +
                BookConstants.SERIAL + " FROM " +
                BookConstants.BOOK + " WHERE " +
                BookConstants.SERIAL + "=" + serial;
        return connection.createStatement().executeQuery(sql);
    }


    public void editDB(int id, Book book) {
        String sql = "UPDATE "
                + BookConstants.BOOK + " SET "
                + BookConstants.NAME_BOOK + " = ?,"
                + BookConstants.AUTHOR + " = ?,"
                + BookConstants.PUBLISHING_YEAR + " = ?,"
                + BookConstants.PUBLISHING_HOUSE + " = ?,"
                + BookConstants.SERIAL + " = ?,"
                + BookConstants.DESCRIPTION + " = ? " + "WHERE "
                + BookConstants.ID + " =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getNameBook());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublishingYear());
            preparedStatement.setString(4, book.getPublishingHouse());
            preparedStatement.setInt(5, book.getSerial());
            preparedStatement.setString(6, book.getDescription());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ObservableList observableList() throws SQLException {
        ResultSet resultSet = BookSql.searchDB();
        while (resultSet.next()) {
            data.add(new BookT(
                    resultSet.getInt(BookConstants.ID),
                    resultSet.getString(BookConstants.NAME_BOOK),
                    resultSet.getString(BookConstants.AUTHOR),
                    resultSet.getInt(BookConstants.PUBLISHING_YEAR),
                    resultSet.getString(BookConstants.PUBLISHING_HOUSE),
                    resultSet.getString(BookConstants.DESCRIPTION),
                    resultSet.getInt(BookConstants.SERIAL),
                    resultSet.getBoolean(BookConstants.FlAG_BOOK)));
        }
        return data;
    }
    public ObservableList observableListFalse() throws SQLException {
        ResultSet resultSet = BookSql.searchDB();
        while (resultSet.next()) {
            if(!resultSet.getBoolean(BookConstants.FlAG_BOOK))
            {
                data.add(new BookT(
                        resultSet.getInt(BookConstants.ID),
                        resultSet.getString(BookConstants.NAME_BOOK),
                        resultSet.getString(BookConstants.AUTHOR),
                        resultSet.getInt(BookConstants.PUBLISHING_YEAR),
                        resultSet.getString(BookConstants.PUBLISHING_HOUSE),
                        resultSet.getString(BookConstants.DESCRIPTION),
                        resultSet.getInt(BookConstants.SERIAL),
                        resultSet.getBoolean(BookConstants.FlAG_BOOK)));
            }
        }
        return data;
    }
    public ObservableList observableListWithHuman(int id) throws SQLException {
        ResultSet resultSet = BookSql.searchWitchHumanDB(id);
        while (resultSet.next()) {
                data.add(new BookT(
                        resultSet.getInt(BookConstants.ID),
                        resultSet.getString(BookConstants.NAME_BOOK),
                        resultSet.getString(BookConstants.AUTHOR),
                        resultSet.getInt(BookConstants.PUBLISHING_YEAR),
                        resultSet.getString(BookConstants.PUBLISHING_HOUSE),
                        resultSet.getString(BookConstants.DESCRIPTION),
                        resultSet.getInt(BookConstants.SERIAL),
                        resultSet.getBoolean(BookConstants.FlAG_BOOK)));
        }
        return data;
    }

    public static boolean checkSearchSerialDB(boolean bool, String serial) {
        int index = 0;
        try {
            ResultSet resultSet = searchSerialDB(Integer.parseInt(serial));
            while (resultSet.next()) {
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (index == 0) return true;
        else if (bool) return true;
        else return false;
        }
        
    }
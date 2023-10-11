package com.bashtan.librarry.sql;

import com.bashtan.librarry.constructor.Book;
import com.bashtan.librarry.constructor.HumanT;
import com.bashtan.librarry.сonstants.APPConstants;
import com.bashtan.librarry.сonstants.BookConstants;
import com.bashtan.librarry.сonstants.GiveBookTableConstants;
import com.bashtan.librarry.сonstants.HumanConstants;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookSql {
    private PreparedStatement preparedStatement = null;
    private static final Connection connection = ConnectDBSql.getConnection();

    public Connection getConnection() {
        return connection;
    }


    public void deleteGiveBook(int id){
        String sql = "DELETE FROM " + GiveBookTableConstants.GIVE_BOOK + " WHERE " + GiveBookTableConstants.ID + " =(?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBookDB(int id) {
        String sql = "UPDATE "
                + BookConstants.BOOK + " SET "
                + BookConstants.FlAG_BOOK + " = ?,"
                + BookConstants.HUMAN_ID + " = null WHERE "
                + BookConstants.ID + " =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editHumanDB(int id) {
        String sql = "UPDATE "
                + HumanConstants.HUMAN + " SET "
                + HumanConstants.FLAG_HUMAN + " = ? WHERE "
                + HumanConstants.ID + " =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private static ResultSet searchWitchHumanDB(int id) throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM " + GiveBookTableConstants.GIVE_BOOK + " WHERE " + GiveBookTableConstants.HUMAN_ID + " = " + id);
    }

    private  ResultSet searchGiveBookIdDB(int humanId,int bookId) throws SQLException {
        return connection.createStatement().executeQuery("SELECT * FROM "
                + GiveBookTableConstants.GIVE_BOOK
                + " WHERE "
                + GiveBookTableConstants.HUMAN_ID + " = " + humanId
                + " AND "
                + GiveBookTableConstants.BOOK_ID + " = " + bookId);
    }

    public int giveBookId(int humanId, int bookId){
        ResultSet resultSet = null;
        int a=0;
        try {
            resultSet = new GetBookSql().searchGiveBookIdDB(humanId,bookId);
            while (resultSet.next()){
                a=resultSet.getInt(GiveBookTableConstants.ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    public int sumHumanWithBook(int id){
        ResultSet resultSet = null;
        int sum=0;
        try {
            resultSet = new GetBookSql().searchWitchHumanDB(id);
            while (resultSet.next()) {
                sum++;
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }
}

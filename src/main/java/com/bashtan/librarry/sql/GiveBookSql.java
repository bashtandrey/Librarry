package com.bashtan.librarry.sql;

import com.bashtan.librarry.сonstants.APPConstants;
import com.bashtan.librarry.сonstants.BookConstants;
import com.bashtan.librarry.сonstants.GiveBookTableConstants;
import com.bashtan.librarry.сonstants.HumanConstants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GiveBookSql {
    private PreparedStatement preparedStatement = null;
    private static final Connection connection = ConnectDBSql.getConnection();

    public Connection getConnection() {
        return connection;
    }

    public void humanId_flagBook_flagHuman(int humanId, int bookId, boolean flagBook, boolean flagHuman){
        String sql = "UPDATE "
                + APPConstants.NAME_BD + "." + BookConstants.BOOK +", "
                + APPConstants.NAME_BD + "." + HumanConstants.HUMAN + " SET "
                + BookConstants.BOOK + "." + BookConstants.HUMAN_ID + " = ? ,"
                + BookConstants.BOOK + "." + BookConstants.FlAG_BOOK + " = ? ,"
                + HumanConstants.HUMAN + "." + HumanConstants.FLAG_HUMAN + " = ? WHERE "
                + BookConstants.BOOK + "." + BookConstants.ID + " = ? AND "
                + HumanConstants.HUMAN + "." + HumanConstants.ID + " = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,humanId);
            preparedStatement.setBoolean(2, flagBook);
            preparedStatement.setBoolean(3,flagHuman);
            preparedStatement.setInt(4,bookId);
            preparedStatement.setInt(5,humanId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void giveBookAdd(int humanId, int bookId, int userId, Date date){
        String sql = "INSERT INTO "
                + APPConstants.NAME_BD + "." + GiveBookTableConstants.GIVE_BOOK + " ("
                + GiveBookTableConstants.HUMAN_ID + ","
                + GiveBookTableConstants.BOOK_ID + ","
                + GiveBookTableConstants.USER_ID + ","
                + GiveBookTableConstants.DATE_IN + ") VALUES((?),(?),(?),(?))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, humanId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setDate(4, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package com.bashtan.librarry;

import com.bashtan.chat.server.ChatServer;
import com.bashtan.librarry.application.Start;
import com.bashtan.librarry.application.StartApplication;
import com.bashtan.librarry.constructor.User;
import com.bashtan.librarry.constructor.UserTest;
import com.bashtan.librarry.sql.CreateDBSql;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class testSQl {

    public static void main(String[] args) throws SQLException, IOException, NoSuchAlgorithmException {
//        Start.start();
//        Start.fileRead();
//        CreateDBSql.create(new User(0,"","","","",0));
//
        UserTest userTest = new UserTest(1,"w","e","w","e",4);
        UserTest userTest1 = new UserTest();
        userTest.getId();
        userTest.setId(1);
        UserTest userTest2 = UserTest.builder()
                .id()
                .login()
                .login()
    }
}

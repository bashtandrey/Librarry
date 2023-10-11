package com.bashtan.librarry.application;

import com.bashtan.librarry.сonstants.APPConstants;

import java.io.File;

public class Start {
    private static  File FILE;
    private static  S_R_FAIL S_R_FAIL;
    public static  boolean FLAG_FILE;
    public static String HOST;
    public static String LOGIN_BD;
    public static String PASSWORD_BD;


    public static void start(){
        FILE = new File(APPConstants.SETTING_KEY);
        S_R_FAIL = new S_R_FAIL(FILE);
        if (FILE.exists()){
          FLAG_FILE = true;
      } else FLAG_FILE = false;
    }

    public static void fileCreate(String string){
        try {
            S_R_FAIL.create(string);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void fileDelete(){
        FILE.delete();

    }
    public static void fileRead()  {
            try {
                String[] answer = S_R_FAIL.read();
                HOST = answer[0];
                LOGIN_BD = answer[1];
                PASSWORD_BD = answer[2];
            } catch (Exception e) {

                  e.printStackTrace();
            }
    }

}

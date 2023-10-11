package com.bashtan.librarry.constructor;

public class User {

    private int id;
    private String login;
    private String password;
    private String userFirstName;
    private String userLastName;

    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String login, String password, String userFirstName, String userLastName, int level) {
        this.login = login;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.level = level;
    }

    public User(int id, String login, String password, String userFirstName, String userLastName, int level) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.level = level;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

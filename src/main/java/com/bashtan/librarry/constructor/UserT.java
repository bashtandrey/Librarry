package com.bashtan.librarry.constructor;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserT {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty user;
    private final SimpleStringProperty password;
    private final SimpleStringProperty UserLastName;
    private final SimpleStringProperty UserFirstName;
    private final SimpleStringProperty level;

    public UserT(int id, String user, String password, String lastName, String firstName, String level) {
        this.id = new SimpleIntegerProperty(id);
        this.user = new SimpleStringProperty(user);
        this.password = new SimpleStringProperty(password);
        this.UserLastName = new SimpleStringProperty(lastName);
        this.UserFirstName = new SimpleStringProperty(firstName);
        this.level = new SimpleStringProperty(level);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public void setUser(String user) {
        this.user.set(user);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUserLastName() {
        return UserLastName.get();
    }

    public SimpleStringProperty userLastNameProperty() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        this.UserLastName.set(userLastName);
    }

    public String getUserFirstName() {
        return UserFirstName.get();
    }

    public SimpleStringProperty userFirstNameProperty() {
        return UserFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.UserFirstName.set(userFirstName);
    }

    public String getLevel() {
        return level.get();
    }

    public SimpleStringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }
}

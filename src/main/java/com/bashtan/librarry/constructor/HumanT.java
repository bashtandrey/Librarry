package com.bashtan.librarry.constructor;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class HumanT{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty firstName;
    private final LocalDate birthday;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty gender;
    private final SimpleBooleanProperty flagHuman;



    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }
    public SimpleBooleanProperty flagHumanProperty() {
        return flagHuman;
    }

    public void setFlagHuman(boolean flagHuman) {
        this.flagHuman.set(flagHuman);
    }
    public boolean getFlagHuman() {
        return flagHuman.get();
    }

    public HumanT(int id, String lastName, String firstName, String birthday, String email, String phone, String gender, boolean flagHuman) {
        this.id = new SimpleIntegerProperty(id);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.birthday = LocalDate.parse(birthday);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.gender = new SimpleStringProperty(gender);
        this.flagHuman = new SimpleBooleanProperty(flagHuman);
    }

}

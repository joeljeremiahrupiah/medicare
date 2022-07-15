package com.moringaschool.medicare.models;

public class User {
    public String userFirstName,userLastName, emailAddress;
    public User() {
    }

    public User(String userFirstName, String userLastName, String emailAddress) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.emailAddress = emailAddress;
    }
}

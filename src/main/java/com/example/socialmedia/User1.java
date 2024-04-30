package com.example.socialmedia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String email;
    private String name;
    private String password;

    public User1() {
    }

    public User1(String userID, String name)
    {
        this.userID=Integer.parseInt(userID);
        this.name=name;
    }

    public User1(String email, String name, String password) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }
}

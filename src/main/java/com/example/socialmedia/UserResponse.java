package com.example.socialmedia;

public class UserResponse {
    private int userID;
    private String name;

    public UserResponse(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


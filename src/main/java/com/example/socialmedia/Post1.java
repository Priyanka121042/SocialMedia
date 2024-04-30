package com.example.socialmedia;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Post1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;


    @Column(columnDefinition = "TEXT")
    private String postBody;

    private int userID;

    @ManyToOne
    private User1 user;

    public Post1() {}

    // Getters and setters
    public Post1(String postBody, int userID) {
        this.postBody = postBody;
        this.userID = userID;
        this.date = new Date();
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}

package com.example.socialmedia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comment1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private String commentBody;
    private int postID;
    private int userID;

    public Comment1(){};

    Comment1(String commentBody, int postID, int userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getPostID() {
        return postID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}

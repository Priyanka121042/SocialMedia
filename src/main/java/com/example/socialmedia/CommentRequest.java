package com.example.socialmedia;

public class CommentRequest {
    private String commentBody;
    private int postID;
    private int userID;

    CommentRequest(){};

    public CommentRequest(String commentBody, int postID, int userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }

    public String getcommentBody() {
        return commentBody;
    }

    public void setcommentBody(String commentBody) {
        this.commentBody = commentBody;
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
}

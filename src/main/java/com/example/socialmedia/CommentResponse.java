package com.example.socialmedia;

public class CommentResponse {
    private int commentID;
    private String commentBody;
    private UserResponse commentCreator;

    public CommentResponse(int commentID, String commentBody, UserResponse commentCreator) {
        this.commentID = commentID;
        this.commentBody = commentBody;
        this.commentCreator = commentCreator;
    }

    // Getters and setters
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

    public UserResponse getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(UserResponse commentCreator) {
        this.commentCreator = commentCreator;
    }
}


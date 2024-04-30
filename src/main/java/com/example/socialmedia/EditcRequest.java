package com.example.socialmedia;

public class EditcRequest {
    private String commentBody;
    private int commentID;

    EditcRequest(){}

    EditcRequest(String commentBody, int commentID) {
        this.commentBody = commentBody;
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}

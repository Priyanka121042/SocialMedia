package com.example.socialmedia;

public class EditRequest {
    private int postID;
    private String postBody;

    EditRequest(int postID, String postBody) {
        this.postID = postID;
        this.postBody = postBody;
    }

    public int getpostID() {
        return postID;
    }

    public String getpostBody() {
        return postBody;
    }
}

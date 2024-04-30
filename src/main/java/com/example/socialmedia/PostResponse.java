package com.example.socialmedia;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class PostResponse {
    private int postID;
    private String postBody;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private List<CommentResponse> comments;

    public PostResponse(int postID, String postBody, Date date, List<CommentResponse> comments) {
        this.postID = postID;
        this.postBody = postBody;
        this.date = date;
        this.comments = comments;
    }

    public int getPostID() {
        return postID;
    }

    public String getPostBody() {
        return postBody;
    }

    public Date getDate() {
        return date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}

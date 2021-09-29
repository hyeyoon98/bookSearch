package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("commentId")
    public long commentId;

    @SerializedName("userId")
    public String userId;

    @SerializedName("comment")
    public String comment;

    @SerializedName("starRate")
    public int starRate;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", starRate=" + starRate +
                '}';
    }
}

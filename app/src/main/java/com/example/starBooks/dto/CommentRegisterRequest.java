package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class CommentRegisterRequest {

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @SerializedName("comment")
    public String comment;

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    @SerializedName("starRate")
    public int starRate;

    public CommentRegisterRequest(String comment, int starRate) {
        this.comment=comment;
        this.starRate=starRate;
    }


}

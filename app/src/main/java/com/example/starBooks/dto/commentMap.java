package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class commentMap {

    public float getAvgStarRate() {
        return avgStarRate;
    }

    public void setAvgStarRate(float avgStarRate) {
        this.avgStarRate = avgStarRate;
    }

    @SerializedName("avgStarRate")
    public float avgStarRate;

    public int getStarRateCount() {
        return starRateCount;
    }

    public void setStarRateCount(int starRateCount) {
        this.starRateCount = starRateCount;
    }

    @SerializedName("starRateCount")
    public int starRateCount;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @SerializedName("comment")
    public List<Comment> commentList;

    @Override
    public String toString() {
        return "commentMap{" +
                "avgStarRate=" + avgStarRate +
                ", starRateCount=" + starRateCount +
                ", commentList=" + commentList +
                '}';
    }
}

package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class map {

    public boolean getIsHeart() {
        return isHeart;
    }

    public void setIsHeart(boolean heart) {
        isHeart = heart;
    }

    @SerializedName("check")
    public boolean isHeart;

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    @SerializedName("heartCount")
    public int heartCount;

    @Override
    public String toString() {
        return "map{" +
                "isHeart=" + isHeart +
                ", heartCount=" + heartCount +
                '}';
    }
}

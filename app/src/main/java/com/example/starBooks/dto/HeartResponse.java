package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class HeartResponse {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @SerializedName("code")
    public String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    public String message;

    public com.example.starBooks.dto.map getMap() {
        return map;
    }

    public void setMap(com.example.starBooks.dto.map map) {
        this.map = map;
    }

    @SerializedName("map")
    public map map;

    @Override
    public String toString() {
        return "HeartRevise{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", map=" + map +
                '}';
    }
}

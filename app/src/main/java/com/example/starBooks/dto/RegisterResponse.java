package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

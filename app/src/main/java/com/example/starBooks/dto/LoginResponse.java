package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;

    @SerializedName("token")
    public String token;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

package com.example.starBooks.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;

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
}

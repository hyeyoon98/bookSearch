package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("id")
    public String id;

    @SerializedName("password")
    public String password;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }
}

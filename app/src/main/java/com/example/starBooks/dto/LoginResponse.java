package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;

    public com.example.starBooks.dto.tokenResponseDto getTokenResponseDto() {
        return tokenResponseDto;
    }

    public void setTokenResponseDto(com.example.starBooks.dto.tokenResponseDto tokenResponseDto) {
        this.tokenResponseDto = tokenResponseDto;
    }

    @SerializedName("tokenResponseDto")
    public tokenResponseDto tokenResponseDto;

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

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", tokenResponseDto=" + tokenResponseDto +
                '}';
    }
}

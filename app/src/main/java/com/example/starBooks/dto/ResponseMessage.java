package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @SerializedName("code")
    public String errorCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    public String message;
}

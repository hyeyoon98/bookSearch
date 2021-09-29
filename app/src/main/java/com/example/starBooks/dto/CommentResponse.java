package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class CommentResponse {

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

    public commentMap getCommentMap() {
        return commentMap;
    }

    public void setCommentMap(commentMap commentMap) {
        this.commentMap = commentMap;
    }

    @SerializedName("commentMap")
    public commentMap commentMap;

    @Override
    public String toString() {
        return "CommentResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", commentMap=" + commentMap +
                '}';
    }
}

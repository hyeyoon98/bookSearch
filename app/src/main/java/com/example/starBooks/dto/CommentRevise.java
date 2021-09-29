package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class CommentRevise {

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @SerializedName("comment")
    public String comment;

    @Override
    public String toString() {
        return "CommentRevise{" +
                "comment='" + comment + '\'' +
                '}';
    }
}

package com.example.starBooks.DTO;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id")
    public int id;

    @SerializedName("imgUrl")
    public String imgUrl;

    @SerializedName("title")
    public String title;

    @SerializedName("author")
    public String author;

    @SerializedName("publisher")
    public String publisher;

    @SerializedName("price")
    public String price;

    @SerializedName("description")
    public String description;

    @SerializedName("createdAt")
    public String createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}


package com.example.starBooks.dto;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
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
    public int price;

    @SerializedName("createdAt")
    public String createAt;

    @SerializedName("description")
    public String description;

    @SerializedName("isbn")
    public String isbn;

    @SerializedName("modifiedAt")
    public String modifiedAt;

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    @BindingAdapter("setImage")
    public static void setImageUrl(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .into(imageView);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", createAt='" + createAt + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                '}';
    }
}


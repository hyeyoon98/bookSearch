package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Page {

    @SerializedName("content")
    public List<Book> content = new ArrayList<>();

    @SerializedName("pageable")
    public pageable pageable;

    @SerializedName("totalElements")
    public int totalElements;

    @SerializedName("totalPages")
    public int totalPages;

    @SerializedName("last")
    public Boolean last;

    @SerializedName("number")
    public int number;

    @SerializedName("sort")
    public sort sortList;

    @SerializedName("size")
    public int size;

    @SerializedName("numberOfElements")
    public int numberOfElements;

    @SerializedName("first")
    public Boolean first;

    @SerializedName("empty")
    public Boolean empty;


    public List<Book> getContent() {
        return content;
    }

    public sort getSortList() {
        return sortList;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public Boolean getFirst() {
        return first;
    }

    public Boolean getLast() {
        return last;
    }

    public int getNumber() {
        return number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public pageable getPageable() {
        return pageable;
    }

    public void setContent(List<Book> content) {
        this.content = content;
    }

    public void setSortList(sort sortList) {
        this.sortList = sortList;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setPageable(pageable pageable) {
        this.pageable = pageable;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    @Override
    public String toString() {
        return "Page{" +
                "content=" + content +
                ", pageable=" + pageable +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                ", number=" + number +
                ", sortList=" + sortList +
                ", size=" + size +
                ", numberOfElements=" + numberOfElements +
                ", first=" + first +
                ", empty=" + empty +
                '}';
    }
}



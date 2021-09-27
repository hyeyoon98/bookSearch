package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class sort {
    @SerializedName("sorted")
    public Boolean sorted;

    @SerializedName("unsorted")
    public Boolean unsorted;

    @SerializedName("empty")
    public Boolean empty;

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public void setSorted(Boolean sorted) {
        this.sorted = sorted;
    }

    public void setUnsorted(Boolean unsorted) {
        this.unsorted = unsorted;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public Boolean getSorted() {
        return sorted;
    }

    public Boolean getUnsorted() {
        return unsorted;
    }

}

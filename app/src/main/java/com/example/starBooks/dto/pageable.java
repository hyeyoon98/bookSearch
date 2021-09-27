package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class pageable {

    @SerializedName("sort")
    public sort sortList;

    @SerializedName("offset")
    public int offset;

    @SerializedName("pageNumber")
    public int pageNumber;

    @SerializedName("pageSize")
    public int pageSize;

    @SerializedName("paged")
    public Boolean paged;

    @SerializedName("unpaged")
    public Boolean unpaged;

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPaged(Boolean paged) {
        this.paged = paged;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortList(sort sortList) {
        this.sortList = sortList;
    }

    public void setUnpaged(Boolean unpaged) {
        this.unpaged = unpaged;
    }

    public Boolean getPaged() {
        return paged;
    }

    public Boolean getUnpaged() {
        return unpaged;
    }

    public int getOffset() {
        return offset;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public sort getSortList() {
        return sortList;
    }


}

package com.example.starBooks.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class pageable {

    @SerializedName("sort")
    public List<sort> sortList;

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

    public void setSortList(List<sort> sortList) {
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

    public List<sort> getSortList() {
        return sortList;
    }

    @Override
    public String toString() {
        return "pageable{" +
                "sortList=" + sortList +
                ", offset=" + offset +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", paged=" + paged +
                ", unpaged=" + unpaged +
                '}';
    }
}

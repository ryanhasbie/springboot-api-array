package com.api.springdemo.model.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PagingResponse<T> extends CommonResponse {

    private List<T> data;

    private long count;

    private int totalPage;

    private int page;

    private int size;

    public PagingResponse(String message, Page<T> page) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = page.getContent();
        this.count = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

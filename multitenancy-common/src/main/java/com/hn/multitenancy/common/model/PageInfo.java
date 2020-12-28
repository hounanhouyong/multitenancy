package com.hn.multitenancy.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfo<T> {
    private int pageNo = 1;
    private int pageSize = 20;
    private int totalCount = 0;
    private List<T> data = new ArrayList<>();

    public PageInfo(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}

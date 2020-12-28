package com.hn.multitenancy.share.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class QueryRequest implements Serializable {
    private static final long serialVersionUID = 6477332466424727253L;
    // 当前页面数据量
    private int pageSize = 10;
    // 当前页码
    private int pageNum = 1;
}

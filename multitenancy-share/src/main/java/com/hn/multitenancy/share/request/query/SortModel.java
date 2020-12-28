package com.hn.multitenancy.share.request.query;

import com.hn.multitenancy.share.request.enums.OrderByEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class SortModel implements Serializable {
    private static final long serialVersionUID = 2744319259658215593L;
    String fieldName;
    /**
     * @see OrderByEnum
     */
    String orderItem;

    public SortModel(String fieldName, String orderItem) {
        this.fieldName = fieldName;
        this.orderItem = orderItem;
    }
}

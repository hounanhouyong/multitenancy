package com.hn.multitenancy.share.request.enums;

public enum OrderByEnum {
    ASC("asc"),
    DESC("desc");

    private String value;

    OrderByEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

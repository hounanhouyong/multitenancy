package com.hn.multitenancy.share.model;

import lombok.Data;

@Data
public class User extends BaseModel {
    private static final long serialVersionUID = -6844567157249433371L;
    private String name;
    private Long tenantId;
}

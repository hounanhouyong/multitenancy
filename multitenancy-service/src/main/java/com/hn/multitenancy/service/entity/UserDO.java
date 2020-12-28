package com.hn.multitenancy.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 5597515876121096919L;
    private String name;
    private Long tenantId;
}

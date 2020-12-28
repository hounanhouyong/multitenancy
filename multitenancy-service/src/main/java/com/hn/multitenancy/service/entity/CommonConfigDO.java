package com.hn.multitenancy.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("common_config")
public class CommonConfigDO extends BaseDO {
    private static final long serialVersionUID = 9115158498824144396L;
    private String groupId;
    private String dataId;
    private String dataKey;
    private String dataVal;
}

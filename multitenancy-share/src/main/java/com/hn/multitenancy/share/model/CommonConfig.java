package com.hn.multitenancy.share.model;

import lombok.Data;

@Data
public class CommonConfig extends BaseModel {
    private static final long serialVersionUID = -430373660406624755L;
    private String groupId;
    private String dataId;
    private String dataKey;
    private String dataVal;
}

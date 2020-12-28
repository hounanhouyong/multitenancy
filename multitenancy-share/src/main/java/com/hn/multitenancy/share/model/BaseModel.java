package com.hn.multitenancy.share.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 5847778466724538905L;
    public Long id;
    public Date gmtCreate;
    public Date gmtModified;
}

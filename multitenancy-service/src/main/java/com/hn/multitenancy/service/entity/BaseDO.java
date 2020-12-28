package com.hn.multitenancy.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 1150003803450505120L;
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;
    public Date gmtCreate;
    public Date gmtModified;
}

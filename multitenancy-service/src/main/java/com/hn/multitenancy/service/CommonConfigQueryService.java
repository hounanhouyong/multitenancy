package com.hn.multitenancy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hn.multitenancy.service.entity.CommonConfigDO;

public interface CommonConfigQueryService extends IService<CommonConfigDO> {

    String findByKey(String key);
}

package com.hn.multitenancy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hn.multitenancy.service.entity.CommonConfigDO;
import com.hn.multitenancy.share.model.CommonConfig;

public interface CommonConfigCommandService extends IService<CommonConfigDO> {

    Long saveCommonConfig(CommonConfig commonConfig);
}

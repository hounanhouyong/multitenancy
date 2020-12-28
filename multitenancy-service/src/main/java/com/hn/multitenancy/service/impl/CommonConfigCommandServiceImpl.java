package com.hn.multitenancy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.multitenancy.service.CommonConfigCommandService;
import com.hn.multitenancy.service.dao.CommonConfigMapper;
import com.hn.multitenancy.service.entity.CommonConfigDO;
import com.hn.multitenancy.service.entity.converter.CommonConfigConverter;
import com.hn.multitenancy.share.model.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class CommonConfigCommandServiceImpl extends ServiceImpl<CommonConfigMapper, CommonConfigDO> implements CommonConfigCommandService {

    @Override
    public Long saveCommonConfig(CommonConfig commonConfig) {
        Date current = new Date();
        commonConfig.setGmtCreate(current);
        commonConfig.setGmtModified(current);
        CommonConfigDO entity = CommonConfigConverter.INSTANCE.to(commonConfig);
        this.save(entity);
        return entity.getId();
    }
}

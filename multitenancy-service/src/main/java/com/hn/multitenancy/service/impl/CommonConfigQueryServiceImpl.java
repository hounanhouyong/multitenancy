package com.hn.multitenancy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.multitenancy.service.CommonConfigQueryService;
import com.hn.multitenancy.service.dao.CommonConfigMapper;
import com.hn.multitenancy.service.entity.CommonConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class CommonConfigQueryServiceImpl extends ServiceImpl<CommonConfigMapper, CommonConfigDO> implements CommonConfigQueryService {

    @Override
    public String findByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        CommonConfigDO entity = this.baseMapper.selectOne(new LambdaQueryWrapper<CommonConfigDO>().eq(CommonConfigDO::getDataKey, key));
        if (entity == null) {
            return null;
        }
        return entity.getDataVal();
    }
}

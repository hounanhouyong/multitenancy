package com.hn.multitenancy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.multitenancy.service.UserCommandService;
import com.hn.multitenancy.service.dao.UserMapper;
import com.hn.multitenancy.service.entity.UserDO;
import com.hn.multitenancy.service.entity.converter.UserConverter;
import com.hn.multitenancy.share.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserCommandServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserCommandService {

    @Override
    public Long saveUser(User user) {
        Date current = new Date();
        user.setGmtCreate(current);
        user.setGmtModified(current);
        UserDO entity = UserConverter.INSTANCE.to(user);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public void updateUserById(User user) {
        Date current = new Date();
        user.setGmtModified(current);
        UserDO entity = UserConverter.INSTANCE.to(user);
        this.updateById(entity);
    }
}

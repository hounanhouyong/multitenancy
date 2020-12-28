package com.hn.multitenancy.service;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hn.multitenancy.service.entity.UserDO;
import com.hn.multitenancy.share.model.User;

public interface UserCommandService extends IService<UserDO> {

    Long saveUser(User user);

    @SqlParser(filter = true)
    void updateUserById(User user);
}

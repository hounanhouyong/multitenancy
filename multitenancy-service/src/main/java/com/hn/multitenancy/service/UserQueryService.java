package com.hn.multitenancy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hn.multitenancy.common.model.PageInfo;
import com.hn.multitenancy.service.entity.UserDO;
import com.hn.multitenancy.share.model.User;
import com.hn.multitenancy.share.request.query.SortModel;
import com.hn.multitenancy.share.request.query.UserQuery;

import java.util.List;

public interface UserQueryService extends IService<UserDO> {

    User findById(Long id);

    Long findTotal(UserQuery query, List<SortModel> sortModels);

    List<User> findUserList(UserQuery query, List<SortModel> sortModels);

    PageInfo<User> findUserPage(UserQuery query, List<SortModel> sortModels);
}

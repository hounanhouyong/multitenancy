package com.hn.multitenancy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.multitenancy.common.model.PageInfo;
import com.hn.multitenancy.service.UserQueryService;
import com.hn.multitenancy.service.dao.UserMapper;
import com.hn.multitenancy.service.entity.UserDO;
import com.hn.multitenancy.service.entity.converter.UserConverter;
import com.hn.multitenancy.service.impl.wrapper.BaseQueryWrapperBuilder;
import com.hn.multitenancy.service.utils.SortUtil;
import com.hn.multitenancy.share.model.User;
import com.hn.multitenancy.share.request.query.SortModel;
import com.hn.multitenancy.share.request.query.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class UserQueryServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserQueryService {
    @Override
    public User findById(Long id) {
        if (id == null) {
            return null;
        }
        return UserConverter.INSTANCE.from(this.baseMapper.selectById(id));
    }

    @Override
    public Long findTotal(UserQuery query, List<SortModel> sortModels) {
        Page<UserDO> page = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<UserDO> queryWrapper = buildQueryWrapper(query, sortModels);
        return this.page(page, queryWrapper).getTotal();
    }

    @Override
    public List<User> findUserList(UserQuery query, List<SortModel> sortModels) {
        Page<UserDO> page = new Page<>(query.getPage(), query.getLimit());
        QueryWrapper<UserDO> queryWrapper = buildQueryWrapper(query, sortModels);
        return UserConverter.INSTANCE.from(this.page(page, queryWrapper).getRecords());
    }

    @Override
    public PageInfo<User> findUserPage(UserQuery query, List<SortModel> sortModels) {
        PageInfo<User> userPageInfo = new PageInfo<>(query.getPage(), query.getLimit());
        Long totalCount = this.findTotal(query, sortModels);
        if (totalCount == 0L) {
            return userPageInfo;
        }
        userPageInfo.setTotalCount(totalCount.intValue());
        userPageInfo.setData(this.findUserList(query, sortModels));
        return userPageInfo;
    }

    private QueryWrapper<UserDO> buildQueryWrapper(UserQuery query, List<SortModel> sortModels) {

        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        // build
        BaseQueryWrapperBuilder.build(queryWrapper, query);

        if (StringUtils.isNotEmpty(query.getUserNameKeywords())) {
            queryWrapper.lambda().like(UserDO::getName, query.getUserNameKeywords());
        }
        if (!CollectionUtils.isEmpty(query.getUserIdList())) {
            queryWrapper.lambda().in(UserDO::getId, query.getUserIdList());
        }

        SortUtil.handleWrapperSort(queryWrapper, sortModels);

        return queryWrapper;
    }

}

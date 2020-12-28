package com.hn.multitenancy.web.system.controller;

import com.hn.multitenancy.common.model.PageInfo;
import com.hn.multitenancy.common.result.layui.Response;
import com.hn.multitenancy.service.UserCommandService;
import com.hn.multitenancy.service.UserQueryService;
import com.hn.multitenancy.share.model.User;
import com.hn.multitenancy.share.request.enums.OrderByEnum;
import com.hn.multitenancy.share.request.query.SortModel;
import com.hn.multitenancy.share.request.query.UserQuery;
import com.hn.multitenancy.web.common.controller.BaseController;
import com.hn.multitenancy.web.common.exception.MultiTenancyException;
import com.hn.multitenancy.web.system.constants.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private UserCommandService userCommandService;

    @GetMapping
    public Response userList(UserQuery userQuery) {
        List<User> userList = new ArrayList<>();
        List<SortModel> sortModels = new ArrayList<>();
        sortModels.add(new SortModel("id", OrderByEnum.DESC.getValue()));
        PageInfo<User> userPageInfo = userQueryService.findUserPage(userQuery, sortModels);
        if (CollectionUtils.isEmpty(userPageInfo.getData())) {
            return new Response().success(0, userList);
        }
        userList = userPageInfo.getData();
        return new Response().success(userPageInfo.getTotalCount(), userList);
    }

    @PostMapping("/save")
    public Response createUser(User user) {
        try {
            Long userId = userCommandService.saveUser(user);
            return new Response().success(userId);
        } catch (Exception e) {
            log.error(SysConstant.MSG_ADD_USER_FAIL, e);
            throw new MultiTenancyException(SysConstant.MSG_ADD_USER_FAIL);
        }
    }

    @PostMapping("/update")
    public Response updateUser(User user) {
        Assert.isTrue(user != null && user.getId() != null, "Null id");
        try {
            userCommandService.updateUserById(user);
            return new Response().success(user.getId());
        } catch (Exception e) {
            log.error(SysConstant.MSG_UPDATE_USER_FAIL, e);
            throw new MultiTenancyException(SysConstant.MSG_UPDATE_USER_FAIL);
        }
    }

}

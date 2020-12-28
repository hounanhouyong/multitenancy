package com.hn.multitenancy.web.system.controller;

import com.hn.multitenancy.common.result.layui.Response;
import com.hn.multitenancy.service.CommonConfigCommandService;
import com.hn.multitenancy.service.CommonConfigQueryService;
import com.hn.multitenancy.share.model.CommonConfig;
import com.hn.multitenancy.web.common.controller.BaseController;
import com.hn.multitenancy.web.common.exception.MultiTenancyException;
import com.hn.multitenancy.web.system.constants.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/config")
public class CommonConfigController extends BaseController {

    @Autowired
    private CommonConfigQueryService commonConfigQueryService;
    @Autowired
    private CommonConfigCommandService commonConfigCommandService;

    @GetMapping
    public Response findByKey(String key) {
        String val = commonConfigQueryService.findByKey(key);
        if (StringUtils.isEmpty(val)) {
            return new Response().success(0, null);
        }
        return new Response().success(1, val);
    }

    @PostMapping("/save")
    public Response createCommonConfig(CommonConfig commonConfig) {
        try {
            Long configId = commonConfigCommandService.saveCommonConfig(commonConfig);
            return new Response().success(configId);
        } catch (Exception e) {
            log.error(SysConstant.MSG_ADD_CONFIG_FAIL, e);
            throw new MultiTenancyException(SysConstant.MSG_ADD_CONFIG_FAIL);
        }
    }

}

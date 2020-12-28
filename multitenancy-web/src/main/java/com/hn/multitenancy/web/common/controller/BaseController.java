package com.hn.multitenancy.web.common.controller;


import com.hn.multitenancy.common.context.RequestContext;

public class BaseController {

    public Long getTenantId() {
        return (Long) RequestContext.get("tenantId");
    }

}

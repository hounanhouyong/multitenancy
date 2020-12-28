package com.hn.multitenancy.web.common.interceptor;

import com.hn.multitenancy.common.context.RequestContext;
import com.hn.multitenancy.service.bean.DBContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String tenantSchema = request.getHeader("tenantSchema");
        if (!StringUtils.isEmpty(tenantSchema)) {
            RequestContext.put("tenantSchema", tenantSchema);
        }
        String tenantId = request.getHeader("tenantId");
        if (!StringUtils.isEmpty(tenantId)) {
            Long tenantID = Long.parseLong(tenantId);
            RequestContext.put("tenantId", tenantID);
            //
            if (tenantID.compareTo(0L) >= 0 && tenantID.compareTo(1024L) <= 0) {
                DBContextHolder.china();
            } else {
                DBContextHolder.singapore();
            }
        }

        return true;
    }

}

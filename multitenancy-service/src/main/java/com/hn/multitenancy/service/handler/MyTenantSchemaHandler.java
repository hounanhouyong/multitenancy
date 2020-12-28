package com.hn.multitenancy.service.handler;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;
import com.hn.multitenancy.common.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MyTenantSchemaHandler implements TenantSchemaHandler {

    private static List<String> IGNORE_TABLE_NAME_LIST = new ArrayList<>(Arrays.asList("common_config"));

    @Override
    public String getTenantSchema() {
        Object obj = RequestContext.get("tenantSchema");
        if (Objects.isNull(obj)) {
            return null;
        }
        String tenantSchema = (String) obj;
        return tenantSchema;
    }

    @Override
    public boolean doTableFilter(String tableName) {
        return IGNORE_TABLE_NAME_LIST.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}

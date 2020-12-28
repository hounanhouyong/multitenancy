package com.hn.multitenancy.service.handler;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.hn.multitenancy.common.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MyTenantHandler implements TenantHandler {

    private static List<String> IGNORE_TABLE_NAME_LIST = new ArrayList<>(Arrays.asList("common_config"));

    @Override
    public Expression getTenantId(boolean where) {
        Object obj = RequestContext.get("tenantId");
        if (Objects.isNull(obj)) {
            return null;
        }
        Long tenantId = (Long) obj;
        return new LongValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean doTableFilter(String tableName) {
        return IGNORE_TABLE_NAME_LIST.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}

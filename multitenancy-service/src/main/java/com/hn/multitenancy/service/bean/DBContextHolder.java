package com.hn.multitenancy.service.bean;

import com.hn.multitenancy.service.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void china() {
        set(DBTypeEnum.CHINA);
        log.info("datasource: china");
    }

    public static void singapore() {
        set(DBTypeEnum.SINGAPORE);
        log.info("datasource: singapore");
    }

}

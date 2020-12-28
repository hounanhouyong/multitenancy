package com.hn.multitenancy.service.aop;

import com.hn.multitenancy.service.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("@annotation(com.hn.multitenancy.service.annotation.China)")
    public void chinaPointcut() {

    }

    @Pointcut("@annotation(com.hn.multitenancy.service.annotation.Singapore)")
    public void singaporePointcut() {

    }

    @Before("chinaPointcut()")
    public void china() {
        DBContextHolder.china();
    }

    @Before("singaporePointcut()")
    public void singapore() {
        DBContextHolder.singapore();
    }

}

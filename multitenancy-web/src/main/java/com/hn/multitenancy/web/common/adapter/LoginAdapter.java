package com.hn.multitenancy.web.common.adapter;

import com.hn.multitenancy.web.common.interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCaching
public class LoginAdapter implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加对用户是否登录的拦截器，并添加过滤项、排除项
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/sysbusy")
                .excludePathPatterns("/404")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/layui/**", "/layui/css/**", "/layui/font/**", "/layui/images/**", "/**/*.tff", "/**/*.woff", "/**/*.woff2")//排除样式、脚本、图片等资源文件
                ;
    }

}

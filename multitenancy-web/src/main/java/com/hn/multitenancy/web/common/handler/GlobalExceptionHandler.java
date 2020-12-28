package com.hn.multitenancy.web.common.handler;

import com.hn.multitenancy.common.result.Response;
import com.hn.multitenancy.web.common.exception.MultiTenancyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new Response().fail(HttpStatus.INTERNAL_SERVER_ERROR,"系统内部异常");
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("系统请求参数错误", e);
        return new Response().fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = MultiTenancyException.class)
    public Response handleMultiTenancyException(MultiTenancyException e) {
        log.error("系统错误", e);
        return new Response().fail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     */
    @ExceptionHandler(BindException.class)
    public Response validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new Response().fail(HttpStatus.BAD_REQUEST, message.toString());
    }

}

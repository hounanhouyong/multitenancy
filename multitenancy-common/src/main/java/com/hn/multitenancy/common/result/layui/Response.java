package com.hn.multitenancy.common.result.layui;

import java.util.HashMap;

/**
 * LayUI返回结构
 */
public class Response extends HashMap<String, Object> {
    private static final long serialVersionUID = -1168978528072557482L;

    public Response code(Integer code) {
        this.put("code", code);
        return this;
    }

    public Response msg(String msg) {
        this.put("msg", msg);
        return this;
    }

    public Response count(Integer count) {
        this.put("count", count);
        return this;
    }

    public Response data(Object data) {
        this.put("data", data);
        return this;
    }

    public Response success() {
        this.code(0);
        this.msg(null);
        return this;
    }

    public Response success(Object data) {
        this.code(0);
        this.msg("success");
        this.data(data);
        return this;
    }

    public Response success(Integer count, Object data) {
        this.code(0);
        this.msg(null);
        this.count(count);
        this.data(data);
        return this;
    }

    public Response fail() {
        this.code(1);
        return this;
    }

    public Response fail(String message) {
        this.code(1);
        this.msg(message);
        return this;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

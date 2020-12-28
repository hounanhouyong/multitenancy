package com.hn.multitenancy.web.common.exception;

public class MultiTenancyException extends RuntimeException {
    public MultiTenancyException() {
        super();
    }
    public MultiTenancyException(String message) {
        super(message);
    }
}

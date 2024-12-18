package com.ms.template.exceptions;

public class ApiException extends RuntimeException {

    private final Integer code;

    public ApiException(String message) {
        super(message);
        this.code = 0;
    }

    public ApiException(ExceptionBase exceptionBase) {
        super(exceptionBase.getMessage());
        this.code = exceptionBase.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

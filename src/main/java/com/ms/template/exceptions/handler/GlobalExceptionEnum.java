package com.ms.template.exceptions.handler;

import com.ms.template.exceptions.ExceptionBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalExceptionEnum implements ExceptionBase {

    NOT_FOUND_EXCEPTION("Not found exception", 404),
    BAD_REQUEST_EXCEPTION("Bad Request exception", 400);

    private final String message;
    private final Integer code;
}

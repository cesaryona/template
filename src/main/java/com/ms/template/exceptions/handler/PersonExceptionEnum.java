package com.ms.template.exceptions.handler;

import com.ms.template.exceptions.ExceptionBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonExceptionEnum implements ExceptionBase {

    PERSON_NOT_FOUND("Person Not found exception", 404),
    EMAIL_ALREADY_EXISTS("Email already exists", 400);

    private final String message;
    private final Integer code;

}

package com.ms.template.controller.response;

public record PersonResponseBody(String firstName,
                                 String lastName,
                                 String document,
                                 String email) {
}

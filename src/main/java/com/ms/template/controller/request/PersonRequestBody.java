package com.ms.template.controller.request;

public record PersonRequestBody(String firstName,
                                String lastName,
                                String document,
                                String email) {
}

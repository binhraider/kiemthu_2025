package com.example.server_register.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessageConstant implements ErrorMessage{
    SUCCESS(200, "Success"),
    BAD_REQUEST(400, "Bad request"),
    NOT_FOUND(404, "Resource not found")
    ;

    private final int code;
    private final String message;
}

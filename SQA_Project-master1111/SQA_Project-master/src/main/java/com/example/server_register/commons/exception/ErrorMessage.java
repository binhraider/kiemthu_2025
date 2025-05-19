package com.example.server_register.commons.exception;

import java.io.Serializable;

public interface ErrorMessage extends Serializable {
    int getCode();
    String getMessage();
}

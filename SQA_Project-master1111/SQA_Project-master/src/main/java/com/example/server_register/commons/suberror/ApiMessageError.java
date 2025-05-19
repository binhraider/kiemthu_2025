package com.example.server_register.commons.suberror;

import lombok.Getter;

@Getter
public class ApiMessageError implements ApiSubError{
    private final String errorMessage;

    public ApiMessageError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}

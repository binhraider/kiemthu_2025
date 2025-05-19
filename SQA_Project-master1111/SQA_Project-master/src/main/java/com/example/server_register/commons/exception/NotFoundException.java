package com.example.server_register.commons.exception;

import com.example.server_register.commons.suberror.ApiSubError;
import lombok.Getter;

@Getter
public class NotFoundException extends RegisterException{
    private final ApiSubError apiSubError;

    public NotFoundException(ErrorMessage errorMessage, ApiSubError apiSubError){
        super(errorMessage);
        this.apiSubError = apiSubError;
    }
}

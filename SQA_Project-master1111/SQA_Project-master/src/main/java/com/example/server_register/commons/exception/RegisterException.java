package com.example.server_register.commons.exception;

import lombok.Getter;

@Getter
public class RegisterException extends RuntimeException{
    private final ErrorMessage errorMessage;

    public RegisterException(ErrorMessage errorMessage){
        super();
        this.errorMessage = errorMessage;
    }
}

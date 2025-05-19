package com.example.server_register.commons;

import com.example.server_register.commons.exception.ErrorMessage;
import com.example.server_register.commons.suberror.ApiSubError;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RegisterRespone <T>{

    private final LocalDateTime localDateTime = LocalDateTime.now();
    private int code;
    private String message;
    private List<?> details;
    private T data;

    public static <T> RegisterRespone<T> build(T data){
        RegisterRespone respone = new RegisterRespone();
        respone.data = data;
        respone.code = 200;
        return respone;
    }

    public static <T> RegisterRespone<T> build(ErrorMessage errorMessage){
        RegisterRespone respone = new RegisterRespone();
        respone.code = errorMessage.getCode();
        respone.message = errorMessage.getMessage();
        return respone;
    }

    public static <T> RegisterRespone<T> build(ErrorMessage errorMessage, List<ApiSubError> details){
        RegisterRespone respone = build(errorMessage);
        respone.details = details;
        return  respone;
    }
}

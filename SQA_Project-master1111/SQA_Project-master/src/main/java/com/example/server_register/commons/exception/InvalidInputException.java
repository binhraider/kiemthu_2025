package com.example.server_register.commons.exception;

import com.example.server_register.commons.suberror.ApiSubError;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class InvalidInputException extends RegisterException{

    private final transient List<ApiSubError> apiSubErrors;

    public InvalidInputException(ErrorMessage errorMessage, ApiSubError apiSubError ){
        super(errorMessage);
        this.apiSubErrors = Collections.singletonList(apiSubError);
    }

    public InvalidInputException(ErrorMessage errorMessage, List<ApiSubError> apiSubErrors){
        super(errorMessage);
        this.apiSubErrors = apiSubErrors;
    }
}

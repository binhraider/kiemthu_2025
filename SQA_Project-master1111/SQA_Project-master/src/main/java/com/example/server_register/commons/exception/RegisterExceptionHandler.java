package com.example.server_register.commons.exception;

import com.example.server_register.commons.RegisterRespone;
import com.example.server_register.commons.suberror.ApiMessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Collections;

@Slf4j
@ControllerAdvice
public class RegisterExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<RegisterRespone<?>> handleInvalidInputException(InvalidInputException exception){
        log.info("handle invalid input exception: Msg = {}", exception.getErrorMessage().getMessage(), exception);
        return new ResponseEntity<>(RegisterRespone.build(exception.getErrorMessage(),
                                                            exception.getApiSubErrors()),
                                                            HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<RegisterRespone<?>> handleNotFoundException(NotFoundException notFoundException){
        log.info("handle not found exception: Msg = {}", notFoundException.getErrorMessage(), notFoundException);
        return new ResponseEntity<>(RegisterRespone.build(notFoundException.getErrorMessage(),
                                                            Collections.singletonList(notFoundException.getApiSubError())),
                                                            HttpStatus.OK);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<RegisterRespone<?>> handleSqlException(SQLException exception){
        log.info("handle sql exception : MSG = {}", exception.getMessage(), exception);
        ApiMessageError apiMessageError = new ApiMessageError(exception.getMessage());
        return new ResponseEntity<>(RegisterRespone.build(ErrorMessageConstant.BAD_REQUEST,
                                                            Collections.singletonList(apiMessageError)), HttpStatus.OK);
    }
}

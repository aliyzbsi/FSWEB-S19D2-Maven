package com.workintech.s18d4.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(EntityException entityException){
        log.error("Entity Exception occured: "+entityException);
        ErrorResponse errorResponse=new ErrorResponse(entityException.getMessage(),entityException.getHttpStatus());
        return new ResponseEntity<>(errorResponse,entityException.getHttpStatus());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(Exception exception){
        log.error("Exception occured: "+exception);
        ErrorResponse errorResponse=new ErrorResponse(exception.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

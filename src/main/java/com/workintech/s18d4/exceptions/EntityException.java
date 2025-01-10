package com.workintech.s18d4.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class EntityException extends RuntimeException {
    private HttpStatus httpStatus;

    public EntityException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

package com.workintech.s18d4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;

}

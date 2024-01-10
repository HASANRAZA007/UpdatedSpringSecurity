package com.uss.UpdatedSpringSecurity.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(CustomServiceException ex) {
        ErrorResponse errorResponse=new ErrorResponse(ex.getErrorCode(),ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomControllerException.class)
    public ResponseEntity<ErrorResponse> handleControllerException(CustomControllerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

package com.uss.UpdatedSpringSecurity.Exception;

import lombok.Getter;

@Getter
public class CustomServiceException extends RuntimeException {

    private final int errorCode;

    public CustomServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}

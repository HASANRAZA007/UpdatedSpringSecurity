package com.uss.UpdatedSpringSecurity.Exception;

import lombok.Getter;

@Getter
public class CustomControllerException extends RuntimeException {
    private final int errorCode;

    public CustomControllerException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}

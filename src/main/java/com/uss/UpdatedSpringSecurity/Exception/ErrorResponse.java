package com.uss.UpdatedSpringSecurity.Exception;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final int errorCode;
    private final String errorMessage;

    public ErrorResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

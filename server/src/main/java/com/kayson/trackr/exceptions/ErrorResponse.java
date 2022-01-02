package com.kayson.trackr.exceptions;

public class ErrorResponse {
    private final Integer status;
    private final String message;

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

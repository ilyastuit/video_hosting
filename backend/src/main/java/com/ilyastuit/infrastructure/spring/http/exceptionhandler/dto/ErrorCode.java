package com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto;

public enum ErrorCode {
    NOT_FOUND(404),
    SERVER_ERROR(500),
    VALIDATION_ERROR(1);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

package com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "message" })
public class ErrorDTO implements HttpErrorDTO {

    private final ErrorCode code;
    private final String message;

    public ErrorDTO(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    @JsonProperty("code")
    public int getCode() {
        return code.getCode();
    }

    @Override
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
}

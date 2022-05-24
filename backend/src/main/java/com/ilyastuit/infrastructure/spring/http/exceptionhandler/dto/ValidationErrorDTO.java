package com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@JsonPropertyOrder({ "code", "message", ValidationErrorDTO.VALIDATION_NODE })
public class ValidationErrorDTO implements HttpErrorDTO {

    public static final String VALIDATION_NODE = "validation-errors";
    public static final String VALIDATION_MESSAGE = "Validation failed.";

    private static final ErrorCode code = ErrorCode.VALIDATION_ERROR;
    private final String message;
    private final Set<? extends ConstraintViolation<? extends Object>> violations;

    public ValidationErrorDTO(Set<? extends ConstraintViolation<? extends Object>> violations) {
        this.violations = violations;

        this.message = VALIDATION_MESSAGE;
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

    @JsonProperty(VALIDATION_NODE)
    public Map<Path, String> getViolations() {
        return violations
                .stream()
                .collect(
                        Collectors.toMap(
                                ConstraintViolation::getPropertyPath,
                                ConstraintViolation::getMessage
                        ));
    }
}

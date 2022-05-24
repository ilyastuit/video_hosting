package com.ilyastuit.model.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException {

    private final transient Set<? extends ConstraintViolation<? extends Object>> violations;

    public ValidationException(Set<? extends ConstraintViolation<? extends Object>> violations) {
        super("Validation errors.");
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<? extends Object>> getViolations() {
        return violations;
    }
}

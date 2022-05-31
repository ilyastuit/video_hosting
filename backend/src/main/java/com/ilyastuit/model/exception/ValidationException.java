package com.ilyastuit.model.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends Exception {

    private final transient Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super("Validation errors.");
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}

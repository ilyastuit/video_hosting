package com.ilyastuit.http.v1.auth.signup.confirm;

import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.exception.ValidationException;
import com.ilyastuit.model.user.usecase.signup.confirm.SignUpConfirmCommand;
import com.ilyastuit.model.user.usecase.signup.confirm.SingUpConfirmHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("/v1/auth/signup/confirm")
public class SignUpConfirmAction {

    private final Validator validator;
    private final SingUpConfirmHandler handler;

    public SignUpConfirmAction(final Validator validator, final SingUpConfirmHandler handler) {
        this.validator = validator;
        this.handler = handler;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> confirm(@RequestBody SignUpConfirmCommand command) throws DomainException, ValidationException {
        Set<ConstraintViolation<SignUpConfirmCommand>> violations = validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        handler.handle(command);

        return ResponseEntity.ok().build();
    }
}

package com.ilyastuit.http.v1.auth.signup;

import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.exception.ValidationException;
import com.ilyastuit.model.user.entity.user.UserId;
import com.ilyastuit.model.user.usecase.signup.request.SignUpRequestCommand;
import com.ilyastuit.model.user.usecase.signup.request.SignUpRequestHandler;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/v1/auth/signup")
public class SignUpRequestAction {

    private final Validator validator;
    private final SignUpRequestHandler handler;

    public SignUpRequestAction(final Validator validator, final SignUpRequestHandler handler) {
        this.validator = validator;
        this.handler = handler;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> handle(@RequestBody SignUpRequestCommand command) throws DomainException, ValidationException {
        Set<ConstraintViolation<SignUpRequestCommand>> violations = validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        UserId id = handler.handle(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(id.getId().toString());
    }
}

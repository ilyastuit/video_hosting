package com.ilyastuit.http.auth.signup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ilyastuit.model.exceptions.DomainException;
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
@RequestMapping("/auth/signup")
public class SignUpRequestAction {

    private final SignUpRequestHandler handler;
    private final Validator validator;

    public SignUpRequestAction(SignUpRequestHandler handler, Validator validator) {
        this.handler = handler;
        this.validator = validator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handle(@RequestBody SignUpRequestCommand command) {
        Set<ConstraintViolation<SignUpRequestCommand>> violations = validator.validate(command);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode response = objectMapper.createObjectNode();

        if (!violations.isEmpty()) {
            ObjectNode validation = objectMapper.createObjectNode();

            for (ConstraintViolation<SignUpRequestCommand> violation: violations) {
                validation.put(violation.getPropertyPath().toString(), violation.getMessage());
            }

            response.set("validation", validation);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.toString());
        }



        try {
            handler.handle(command);
        } catch (DomainException e) {
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.toString());
        }

        response.put("email", command.email);

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}

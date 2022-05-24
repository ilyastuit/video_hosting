package com.ilyastuit.infrastructure.spring.http.exceptionhandler;

import com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto.ErrorCode;
import com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto.ErrorDTO;
import com.ilyastuit.infrastructure.spring.http.exceptionhandler.dto.ValidationErrorDTO;
import com.ilyastuit.model.exception.DomainException;
import com.ilyastuit.model.exception.EntityNotFoundException;
import com.ilyastuit.model.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    protected ResponseEntity<ErrorDTO> handleDomainException(DomainException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(ErrorCode.SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(ErrorCode.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<ValidationErrorDTO> handleValidationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorDTO(e.getViolations()));
    }

}

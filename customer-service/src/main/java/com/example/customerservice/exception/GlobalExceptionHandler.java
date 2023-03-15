package com.example.customerservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> errors = new HashMap<>();
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(err -> validationErrors.put(((FieldError)err).getField(), err.getDefaultMessage()));

        errors.put("message", "Validation Error(s)");
        errors.put("timestamp", new Date().toString());
        errors.put("details", validationErrors);

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", "Resource not found.");
        errors.put("details", exception.getMessage());
        errors.put("timestamp", new Date().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistException(EmailAlreadyExistException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", "Email already exists");
        errors.put("details", exception.getMessage());
        errors.put("timestamp", new Date().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(SSNException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistException(SSNException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("message", "SSN error.");
        errors.put("details", exception.getMessage());
        errors.put("timestamp", new Date().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

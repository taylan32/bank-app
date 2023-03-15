package com.example.accountservice.exception;

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

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Resource not found");
        errors.put("details", exception.getMessage());
        errors.put("timestamp", new Date().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
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



}

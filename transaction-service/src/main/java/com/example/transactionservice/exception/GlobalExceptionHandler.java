package com.example.transactionservice.exception;

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

    @ExceptionHandler(InSufficientBalanceException.class)
    public ResponseEntity<Object> handleInSufficientBalanceException(InSufficientBalanceException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Transaction error.");
        error.put("timestamp", new Date().toString());
        error.put("details", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InvalidSenderException.class)
    public ResponseEntity<Object> handleInSufficientBalanceException(InvalidSenderException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Invalid sender.");
        error.put("timestamp", new Date().toString());
        error.put("details", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}

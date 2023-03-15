package com.example.transactionservice.exception;

public class NotFoundException extends RuntimeException{

    private ExceptionMessage exceptionMessage;

    public NotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

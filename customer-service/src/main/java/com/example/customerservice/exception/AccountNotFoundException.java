package com.example.customerservice.exception;

public class AccountNotFoundException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public AccountNotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}

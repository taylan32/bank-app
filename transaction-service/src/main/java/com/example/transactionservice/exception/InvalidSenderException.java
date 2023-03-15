package com.example.transactionservice.exception;

public class InvalidSenderException extends RuntimeException{

    public InvalidSenderException(String message) {
        super(message);
    }
}

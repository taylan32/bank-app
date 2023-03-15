package com.example.transactionservice.exception;

public class InSufficientBalanceException extends RuntimeException {

    public InSufficientBalanceException(String message) {
        super(message);
    }
}

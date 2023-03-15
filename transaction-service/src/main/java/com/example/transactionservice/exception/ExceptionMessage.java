package com.example.transactionservice.exception;

public record ExceptionMessage(
        String timestamp,
        int httpStatus,
        String title,
        String message,
        String path
) {
}

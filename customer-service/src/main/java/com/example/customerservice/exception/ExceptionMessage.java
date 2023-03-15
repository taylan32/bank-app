package com.example.customerservice.exception;

public record ExceptionMessage(
        String timestamp,
        int httpStatus,
        String title,
        String message,
        String path
) {
}

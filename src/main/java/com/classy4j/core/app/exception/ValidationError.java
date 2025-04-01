package com.classy4j.core.app.exception;

public class ValidationError extends RuntimeException {
    public ValidationError(String message) {
        super(message);
    }

    public ValidationError(String message, Throwable cause) {
        super(message, cause);
    }
}
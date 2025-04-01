package com.classy4j.exception;

public class RateLimitError extends AppException {
    public RateLimitError(String message) {
        super(message);
    }

    public RateLimitError(String message, Throwable cause) {
        super(message, cause);
    }
}
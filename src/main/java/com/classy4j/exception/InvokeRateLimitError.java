package com.classy4j.exception;

public class InvokeRateLimitError extends RateLimitError {
    public InvokeRateLimitError(String message) {
        super(message);
    }

    public InvokeRateLimitError(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.classy4j.exception;

public class AppInvokeQuotaExceededError extends RateLimitError {
    public AppInvokeQuotaExceededError(String message) {
        super(message);
    }

    public AppInvokeQuotaExceededError(String message, Throwable cause) {
        super(message, cause);
    }
}
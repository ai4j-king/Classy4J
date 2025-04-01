package com.classy4j.core.app.exception;

public class InvokeAuthorizationError extends RuntimeException {
    public InvokeAuthorizationError(String message) {
        super(message);
    }

    public InvokeAuthorizationError(String message, Throwable cause) {
        super(message, cause);
    }
}
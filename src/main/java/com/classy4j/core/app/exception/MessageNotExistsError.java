package com.classy4j.core.app.exception;

public class MessageNotExistsError extends RuntimeException {
    public MessageNotExistsError(String message) {
        super(message);
    }

    public MessageNotExistsError(String message, Throwable cause) {
        super(message, cause);
    }
}
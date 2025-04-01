package com.classy4j.core.app.exception;

/**
 * 内容审核错误异常
 */
public class ModerationError extends RuntimeException {
    public ModerationError() {
        super();
    }

    public ModerationError(String message) {
        super(message);
    }

    public ModerationError(String message, Throwable cause) {
        super(message, cause);
    }

    public ModerationError(Throwable cause) {
        super(cause);
    }
}
package com.classy4j.exception;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

class AppUnavailableException extends AppException {
    public AppUnavailableException(String message) {
        super(message);
    }
}

class CompletionRequestException extends AppException {
    public CompletionRequestException(String message) {
        super(message);
    }
}

class ConversationCompletedException extends AppException {
    public ConversationCompletedException(String message) {
        super(message);
    }
}

class NotChatAppException extends AppException {
    public NotChatAppException(String message) {
        super(message);
    }
}

class ProviderModelCurrentlyNotSupportException extends AppException {
    public ProviderModelCurrentlyNotSupportException(String message) {
        super(message);
    }
}

class ProviderNotInitializeException extends AppException {
    public ProviderNotInitializeException(String message) {
        super(message);
    }
}

class ProviderQuotaExceededException extends AppException {
    public ProviderQuotaExceededException(String message) {
        super(message);
    }
}
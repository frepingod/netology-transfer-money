package ru.netology.exception;

public class ConfirmationException extends RuntimeException {

    public ConfirmationException() {
    }

    public ConfirmationException(String message) {
        super(message);
    }

    public ConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfirmationException(Throwable cause) {
        super(cause);
    }

    public ConfirmationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
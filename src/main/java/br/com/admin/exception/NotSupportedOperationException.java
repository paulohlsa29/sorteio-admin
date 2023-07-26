package br.com.admin.exception;

public class NotSupportedOperationException extends RuntimeException {

    public NotSupportedOperationException() {
        super();
    }

    public NotSupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedOperationException(String message) {
        super(message);
    }

    public NotSupportedOperationException(Throwable cause) {
        super(cause);
    }
}

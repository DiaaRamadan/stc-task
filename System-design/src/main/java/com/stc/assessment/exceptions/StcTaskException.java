package com.stc.assessment.exceptions;

public class StcTaskException extends RuntimeException {

    public static final String MESSAGE_FORMAT = "Some errors happens";

    public StcTaskException() {
    }

    public StcTaskException(String message) {
        super(message);
    }

    public StcTaskException(Throwable cause) {
        super(cause);
    }

    public StcTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public StcTaskException(String entity, String field, String value) {
        super(MESSAGE_FORMAT);
    }
}

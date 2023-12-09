package com.stc.assessment.exceptions;

import java.io.Serial;

public class BadRequestException extends StcTaskException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }


}

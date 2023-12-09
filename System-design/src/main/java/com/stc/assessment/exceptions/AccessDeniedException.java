package com.stc.assessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends StcTaskException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }


}

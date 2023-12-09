package com.stc.assessment.exceptions;

import com.stc.assessment.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandler {

    private static final String ERROR_MSG_INVALID_REQUEST_PARAMETERS = "Invalid request parameters";


    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach(obj -> errors.put(obj.getField(), obj.getDefaultMessage()));
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ERROR_MSG_INVALID_REQUEST_PARAMETERS, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.FORBIDDEN.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(StcTaskException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(StcTaskException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);

    }
}

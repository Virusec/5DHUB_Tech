package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Anatoliy Shikin
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value()
                , exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        log.error("Data integrity violation: {}", exception.getMessage());

        String detailedMessage = exception.getMostSpecificCause().getMessage();
        String userMessage = "An error occurred while saving the data.";

        if (detailedMessage != null && detailedMessage.contains("unique_company_name")) {
            userMessage = "A company with that name already exists. Please choose a different name.";
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), userMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
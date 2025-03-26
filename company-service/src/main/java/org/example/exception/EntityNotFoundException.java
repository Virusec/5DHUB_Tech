package org.example.exception;

/**
 * @author Anatoliy Shikin
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
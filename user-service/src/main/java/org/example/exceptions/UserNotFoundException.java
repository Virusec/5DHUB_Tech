package org.example.exceptions;

/**
 * @author Anatoliy Shikin
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " was not found!");
    }
    public UserNotFoundException(String lastName) {
        super("User with last name " + lastName + " was not found!");
    }
}

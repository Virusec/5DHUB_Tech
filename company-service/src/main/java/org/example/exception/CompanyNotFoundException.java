package org.example.exception;

/**
 * @author Anatoliy Shikin
 */
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super("Company with id " + id + " was not found!");
    }
    public CompanyNotFoundException(String name) {
        super("Company with name " + name + " was not found!");
    }
}

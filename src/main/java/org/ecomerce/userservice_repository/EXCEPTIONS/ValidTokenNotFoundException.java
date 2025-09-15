package org.ecomerce.userservice_repository.EXCEPTIONS;

public class ValidTokenNotFoundException extends RuntimeException {
    public ValidTokenNotFoundException(String message) {
        super(message);
    }
}

package com.senla.electric.scooter.rental.exceptions;

public class PermissionDeniedException extends RuntimeException{

    public PermissionDeniedException(String message) {
        super(message);
    }
}

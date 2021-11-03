package com.senla.electric.scooter.rental.exceptions;

public class BadDataException extends RuntimeException{

    public BadDataException(String message) {
        super(message);
    }
}

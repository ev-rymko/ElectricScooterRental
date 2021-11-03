package com.senla.electric.scooter.rental.exceptions;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(String message) {
        super(message);
    }
}

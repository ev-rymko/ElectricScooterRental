package com.senla.electric.scooter.rental.exceptions;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }
}

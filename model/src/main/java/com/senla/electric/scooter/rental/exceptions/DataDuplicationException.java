package com.senla.electric.scooter.rental.exceptions;

public class DataDuplicationException extends RuntimeException{

    public DataDuplicationException(String message) {
        super(message);
    }
}

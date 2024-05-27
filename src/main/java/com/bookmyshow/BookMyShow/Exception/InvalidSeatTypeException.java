package com.bookmyshow.BookMyShow.Exception;

public class InvalidSeatTypeException extends RuntimeException{

    public InvalidSeatTypeException() {
        super();
    }

    public InvalidSeatTypeException(String message) {
        super(message);
    }
}

package com.bookmyshow.BookMyShow.Exception;

public class ShowSeatNotAvailableException extends RuntimeException{

    public ShowSeatNotAvailableException() {
        super();
    }

    public ShowSeatNotAvailableException(String message) {
        super(message);
    }
}
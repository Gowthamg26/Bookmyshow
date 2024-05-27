package com.bookmyshow.BookMyShow.Exception;

public class InvalidArgumentException extends RuntimeException{

    public InvalidArgumentException() {
        super();
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}

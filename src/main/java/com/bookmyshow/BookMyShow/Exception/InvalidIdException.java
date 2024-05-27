package com.bookmyshow.BookMyShow.Exception;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }

}

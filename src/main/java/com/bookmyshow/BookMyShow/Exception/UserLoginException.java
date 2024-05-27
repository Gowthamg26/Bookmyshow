package com.bookmyshow.BookMyShow.Exception;

public class UserLoginException extends RuntimeException{

    public UserLoginException() {
        super();
    }

    public UserLoginException(String message) {
        super(message);
    }
}

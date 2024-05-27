package com.bookmyshow.BookMyShow.Exception;

public class UserSignupException extends RuntimeException{

    public UserSignupException() {
        super();
    }

    public UserSignupException(String message) {
        super(message);
    }
}

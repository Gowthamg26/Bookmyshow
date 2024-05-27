package com.bookmyshow.BookMyShow.Exception;

public class PaymentFailedException extends RuntimeException{

    public PaymentFailedException() {
        super();
    }

    public PaymentFailedException(String message) {
        super(message);
    }
}

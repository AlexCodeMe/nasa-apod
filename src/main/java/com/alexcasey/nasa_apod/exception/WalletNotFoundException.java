package com.alexcasey.nasa_apod.exception;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String message) {
        super(message);
    }

    public WalletNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.azrin.auth.ExceptionHandler;

public class NotFound extends RuntimeException {
    public NotFound(String message) {
        super(message);
    }
}

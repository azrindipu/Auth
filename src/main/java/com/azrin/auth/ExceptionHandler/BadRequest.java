package com.azrin.auth.ExceptionHandler;

public class BadRequest extends RuntimeException {
    public BadRequest(String message){
        super(message);
    }
}

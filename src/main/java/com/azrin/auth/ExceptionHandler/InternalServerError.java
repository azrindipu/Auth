package com.azrin.auth.ExceptionHandler;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message){
        super(message);
    }
}

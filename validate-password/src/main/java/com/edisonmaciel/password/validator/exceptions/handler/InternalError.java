package com.edisonmaciel.password.validator.exceptions.handler;

public class InternalError extends RuntimeException {
    public InternalError(String message, Throwable cause) {
        super(message, cause);
    }
}
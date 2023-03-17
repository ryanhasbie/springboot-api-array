package com.api.springdemo.exception;

public class ExistException extends RuntimeException {
    public ExistException() {
        super("Data is exist");
    }

    public ExistException(String message) {
        super(message);
    }
}

package com.api.springdemo.exception;

import org.springframework.http.HttpStatus;

public class RestTemplateException extends RuntimeException {
    public RestTemplateException(String serviceName, HttpStatus status, String error) {
        super(error + " at " + serviceName + " (" + status.value() + ")");
    }
}

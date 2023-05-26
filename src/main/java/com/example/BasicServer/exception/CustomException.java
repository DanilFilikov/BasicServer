package com.example.BasicServer.exception;

import com.example.BasicServer.error.ValidationConstants;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
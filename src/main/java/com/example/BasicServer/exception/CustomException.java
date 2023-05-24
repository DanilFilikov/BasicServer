package com.example.BasicServer.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
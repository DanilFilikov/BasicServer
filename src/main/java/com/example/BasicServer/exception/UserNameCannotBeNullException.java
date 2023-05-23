package com.example.BasicServer.exception;

public class UserNameCannotBeNullException extends Exception{
    public UserNameCannotBeNullException(String message) {
        super(message);
    }
}
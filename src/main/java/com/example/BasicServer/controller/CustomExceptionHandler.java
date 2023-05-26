package com.example.BasicServer.controller;

import com.example.BasicServer.error.ErrorCodes;
import com.example.BasicServer.exception.CustomException;
import com.example.BasicServer.model.CustomSuccessResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object>handle(CustomException e){
        return new ResponseEntity<>(CustomSuccessResponse.getErrorResponse(e.getMessage(),ErrorCodes.getError(e.getMessage()) ), HttpStatus.NOT_FOUND);
    }
}
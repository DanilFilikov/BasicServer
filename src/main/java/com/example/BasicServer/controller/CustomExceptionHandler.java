package com.example.BasicServer.controller;

import com.example.BasicServer.error.ErrorCodes;
import com.example.BasicServer.model.CustomSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object>handle(MethodArgumentNotValidException e){
        List<Integer> codes = e.getBindingResult().getAllErrors()
                .stream()
                .map(element -> ErrorCodes.getError(e.getMessage()))
                .toList();
        return new ResponseEntity<>(CustomSuccessResponse.getErrorResponse(codes.get(0), codes), HttpStatus.BAD_REQUEST);
    }
}
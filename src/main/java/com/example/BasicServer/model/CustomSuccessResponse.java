package com.example.BasicServer.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomSuccessResponse<T> {

    private T data;
    private int statusCode;
    private boolean success;
    private Integer code;
    private List<Integer> codesList;

    public static <T> CustomSuccessResponse<T> getSuccessResponse(T data){
        CustomSuccessResponse<T> successResponse = new CustomSuccessResponse<>();
        successResponse.setData(data);
        successResponse.setStatusCode(1);
        successResponse.setSuccess(true);
        return successResponse;
    }

    public static <T> CustomSuccessResponse<T> getErrorResponse(List<Integer> codesList, Integer codes){
        CustomSuccessResponse<T> successResponse = new CustomSuccessResponse<>();
        successResponse.setCodesList(codesList);
        successResponse.setStatusCode(codes);
        successResponse.setSuccess(true);
        return successResponse;
    }
}
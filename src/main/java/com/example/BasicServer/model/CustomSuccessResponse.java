package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.error.ErrorCodes;
import lombok.Data;

import java.util.List;

@Data
public class CustomSuccessResponse {

    private Object data;
    private int statusCode;
    private boolean success;
    private Integer code;
    List<Integer> codesList;

    public static CustomSuccessResponse getSuccessResponse(Object body){
        CustomSuccessResponse successResponse = new CustomSuccessResponse();
        successResponse.setData(body);
        successResponse.setStatusCode(1);
        successResponse.setSuccess(true);
        return successResponse;
    }

    public static CustomSuccessResponse getErrorResponse(List<Integer> codesList, Integer codes){
        CustomSuccessResponse successResponse = new CustomSuccessResponse();
        successResponse.setCodesList(codesList);
        successResponse.setStatusCode(codes);
        successResponse.setSuccess(true);
        return successResponse;
    }
}
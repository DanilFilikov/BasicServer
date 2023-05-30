package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import lombok.Data;

@Data
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;

    public static BaseSuccessResponse getSuccessResponse() {
        BaseSuccessResponse baseSuccessResponse = new BaseSuccessResponse();
        baseSuccessResponse.setSuccess(true);
        baseSuccessResponse.setStatusCode(1);
        return baseSuccessResponse;
    }
}
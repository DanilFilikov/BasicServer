package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import lombok.Data;

@Data
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;

    public static BaseSuccessResponse getSuccessResponse() {
        BaseSuccessResponse model = new BaseSuccessResponse();
        model.setSuccess(true);
        model.setStatusCode(1);
        return model;
    }
}
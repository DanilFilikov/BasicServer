package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import lombok.Data;

@Data
public class BaseSuccessResponse {
    private Long statusCode;
    private boolean success;

    public static BaseSuccessResponse getSuccessResponse(TaskEntity task) {
        BaseSuccessResponse model = new BaseSuccessResponse();
        model.setSuccess(true);
        model.setStatusCode(1L);
        return model;
    }
}
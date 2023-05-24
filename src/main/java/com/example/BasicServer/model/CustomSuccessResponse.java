package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import lombok.Data;

import java.util.List;

@Data
public class CustomSuccessResponse {

    private GetNewsDto getNewsDto;
    private Long statusCode;
    private boolean success;

    public static CustomSuccessResponse getSuccessResponse(GetNewsDto getNewsDto){
        CustomSuccessResponse successResponse = new CustomSuccessResponse();
        successResponse.setGetNewsDto(getNewsDto);
        successResponse.setStatusCode(1L);
        successResponse.setSuccess(true);
        return successResponse;
    }
}
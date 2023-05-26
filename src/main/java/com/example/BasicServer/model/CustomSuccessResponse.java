package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.error.ErrorCodes;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomSuccessResponse {

    private GetNewsDto getNewsDto;
    private Long statusCode;
    private boolean success;
    private String error;
    private Integer code;

    public static CustomSuccessResponse getSuccessResponse(GetNewsDto getNewsDto){
        CustomSuccessResponse successResponse = new CustomSuccessResponse();
        successResponse.setGetNewsDto(getNewsDto);
        successResponse.setStatusCode(1L);
        successResponse.setSuccess(true);
        return successResponse;
    }

    public static CustomSuccessResponse getErrorResponse(String error, Integer codes){
        CustomSuccessResponse successResponse = new CustomSuccessResponse();
        successResponse.setError(error);
        successResponse.setCode(codes);
        return successResponse;
    }
}
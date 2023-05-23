package com.example.BasicServer.model;

import lombok.Data;

@Data
public class BaseSuccessResponse {
    private int statusCode;
    private boolean success;
/*
    public static BaseSuccessResponse toModel(TaskEntity task){
        BaseSuccessResponse model = new BaseSuccessResponse();;
    }*/
}
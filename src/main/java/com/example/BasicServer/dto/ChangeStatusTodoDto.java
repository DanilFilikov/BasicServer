package com.example.BasicServer.dto;

import com.example.BasicServer.error.ValidationConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangeStatusTodoDto {

    @NotNull(message = ValidationConstants.TODO_STATUS_NOT_NULL)
    private Boolean status;

}
package com.example.BasicServer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangeStatusTodoDto {
    private boolean status;
}
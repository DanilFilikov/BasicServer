package com.example.BasicServer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ChangeStatusTodoDto {
    @NotEmpty(message = "Body can't be empty")
    private boolean status;
}
package com.example.BasicServer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChangeTextTodoDto {

    @NotNull
    @Size(min = 3, max = 160)
    private String text;

}

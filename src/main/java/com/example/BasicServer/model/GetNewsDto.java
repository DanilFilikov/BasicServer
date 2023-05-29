package com.example.BasicServer.model;

import com.example.BasicServer.entity.TaskEntity;
import lombok.Data;

import java.util.List;

@Data
public class GetNewsDto {
    private List<TaskEntity> content;
    private Long notReady;
    private Long numberOfElements;
    private Long ready;

    public static GetNewsDto getNewsDto(List<TaskEntity> task){
        GetNewsDto getNewsDto = new GetNewsDto();
        getNewsDto.setContent(task);
        getNewsDto.setNotReady(task.stream().filter(taskEntity -> !taskEntity.getStatus()).count());
        getNewsDto.setReady(task.stream().filter(TaskEntity::getStatus).count());
        getNewsDto.setNumberOfElements((long) task.size());
        return getNewsDto;
    }
}
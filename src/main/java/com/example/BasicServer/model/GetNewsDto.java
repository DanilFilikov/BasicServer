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

    public static GetNewsDto GetNewsDto(List<TaskEntity> task){
        GetNewsDto getNewsDto = new GetNewsDto();
        getNewsDto.setContent(task);
        getNewsDto.setNotReady(task.stream().filter(taskEntity -> !taskEntity.isStatus()).count());
        getNewsDto.setReady(task.stream().filter(TaskEntity::isStatus).count());
        getNewsDto.setNumberOfElements((long) task.size());
        return getNewsDto;
    }
}
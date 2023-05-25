package com.example.BasicServer.service;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.error.ValidationConstants;
import com.example.BasicServer.model.BaseSuccessResponse;
import com.example.BasicServer.model.CustomSuccessResponse;
import com.example.BasicServer.model.GetNewsDto;
import com.example.BasicServer.repository.TodoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TodoRepo todoRepo;

    public BaseSuccessResponse save(CreateTodoDto todo) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setText(todo.getText());
        return BaseSuccessResponse.toModel(todoRepo.save(taskEntity));
    }

    public Object getPaginated(@NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1) Integer page,
                               @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
                               @Max(value = 100, message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
                               Integer perPage, Boolean status) {
        if (status == null) {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.GetNewsDto(todoRepo.
                    findAll(PageRequest.of(page, perPage))
                    .getContent()));
        } else {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.GetNewsDto(todoRepo.
                    findAll(PageRequest.of(page, perPage))
                    .getContent()
                    .stream()
                    .filter(taskEntity -> taskEntity.isStatus() == status)
                    .toList()));
        }
    }

    public BaseSuccessResponse delete(Long id) throws MethodArgumentNotValidException {
        todoRepo.deleteById(id);
        return BaseSuccessResponse.toModel(todoRepo.findById(id).get());
    }

    public BaseSuccessResponse deleteAllReady() {
        for (TaskEntity entity : todoRepo.findAll()
        ) {
            if (entity.isStatus()) {
                todoRepo.delete(entity);
            }
        }
        return BaseSuccessResponse.toModel((TaskEntity) todoRepo.findAll());
    }

    public TaskEntity patchStatus(@Validated ChangeStatusTodoDto task, Long id) {
        Optional<TaskEntity> taskEntity = todoRepo.findById(id);
        taskEntity.get().setStatus(task.isStatus());
        return todoRepo.save(taskEntity.get());
    }

    public TaskEntity patchText(@Validated ChangeTextTodoDto task, Long id) {
        Optional<TaskEntity> taskEntity = todoRepo.findById(id);
        taskEntity.get().setText(task.getText());
        return todoRepo.save(taskEntity.get());
    }

    public void patch(@Validated ChangeStatusTodoDto task) {
        for (TaskEntity entity : todoRepo.findAll()
        ) {
            entity.setStatus(task.isStatus());
            todoRepo.save(entity);
        }
    }
}

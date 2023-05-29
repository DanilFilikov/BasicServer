package com.example.BasicServer.service;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.error.ValidationConstants;
import com.example.BasicServer.exception.CustomException;
import com.example.BasicServer.model.BaseSuccessResponse;
import com.example.BasicServer.model.CustomSuccessResponse;
import com.example.BasicServer.model.GetNewsDto;
import com.example.BasicServer.repository.TodoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
public class TaskService {

    @Autowired
    TodoRepo todoRepo;

    public CustomSuccessResponse save(CreateTodoDto todo) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setText(todo.getText());
        todoRepo.save(taskEntity);
        return CustomSuccessResponse.getSuccessResponse(taskEntity);
    }

    public Object getPaginated(@NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1) Integer page,
                               @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
                               @Max(value = 100, message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
                               Integer perPage, Boolean status) {
        if (status == null) {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.getNewsDto(todoRepo.
                    findAll(PageRequest.of(page, perPage))
                    .getContent()));
        } else {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.getNewsDto(todoRepo.
                    findAll(PageRequest.of(page, perPage))
                    .getContent()
                    .stream()
                    .filter(taskEntity -> taskEntity.getStatus() == status)
                    .toList()));
        }
    }

    public BaseSuccessResponse delete(Long id) {
        if(todoRepo.findById(id).isEmpty()) {
            throw new CustomException(ValidationConstants.TASK_NOT_FOUND);
        }
        todoRepo.deleteById(id);
        return BaseSuccessResponse.getSuccessResponse();
    }

    public BaseSuccessResponse deleteAllReady() {
        todoRepo.findAll().stream().filter(TaskEntity::getStatus).forEach(todoRepo::delete);
        return BaseSuccessResponse.getSuccessResponse();
    }

    public BaseSuccessResponse patchStatus(ChangeStatusTodoDto task, Long id)  {
        if(todoRepo.findById(id).isEmpty()) {
            throw new CustomException(ValidationConstants.TASK_NOT_FOUND);
        }
        TaskEntity tasksEntity = todoRepo.findById(id).get();
        tasksEntity.setStatus(task.getStatus());
        todoRepo.save(tasksEntity);
        return BaseSuccessResponse.getSuccessResponse();
    }

    public BaseSuccessResponse patchText(ChangeTextTodoDto task, Long id){
        if(todoRepo.findById(id).isEmpty()){
            throw new CustomException(ValidationConstants.TASK_NOT_FOUND);
        }
        todoRepo.findById(id).get().setText(task.getText());
        todoRepo.save(todoRepo.findById(id).get());
        return BaseSuccessResponse.getSuccessResponse();
    }

    public BaseSuccessResponse patch(ChangeStatusTodoDto task) {
        todoRepo.updateStatus(task.getStatus());
        return BaseSuccessResponse.getSuccessResponse();
    }
}

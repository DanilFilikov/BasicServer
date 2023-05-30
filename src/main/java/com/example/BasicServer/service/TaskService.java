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
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
public class TaskService {

    @Autowired
    private TodoRepo todoRepo;

    public CustomSuccessResponse<TaskEntity> createTask(CreateTodoDto todo) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setText(todo.getText());
        todoRepo.save(taskEntity);
        return CustomSuccessResponse.getSuccessResponse(taskEntity);
    }

    public CustomSuccessResponse<GetNewsDto> getPaginated(@NotNull(message = ValidationConstants.PARAM_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PAGE_GREATER_OR_EQUAL_1) Integer page,
                                                          @NotNull(message = ValidationConstants.PARAM_PER_PAGE_NOT_NULL)
                               @Min(value = 1, message = ValidationConstants.TASKS_PER_PAGE_GREATER_OR_EQUAL_1)
                               @Max(value = 100, message = ValidationConstants.TASKS_PER_PAGE_LESS_OR_EQUAL_100)
                               Integer perPage, Boolean status) {
        if (status == null) {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.mapToDto(todoRepo.
                    findAll(PageRequest.of(page, perPage))
                    .getContent()));
        } else {
            return CustomSuccessResponse.getSuccessResponse(GetNewsDto.mapToDto(todoRepo.
                    findByStatus(status)));
        }
    }

    public BaseSuccessResponse delete(Long id) {
        todoRepo.findById(id).orElseThrow(() -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
        todoRepo.deleteById(id);
        return BaseSuccessResponse.getSuccessResponse();
    }

    public BaseSuccessResponse deleteAllReady() {
        todoRepo.deleteByStatus(true);
        return BaseSuccessResponse.getSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse patchStatus(ChangeStatusTodoDto task, Long id) {
        todoRepo.findById(id).orElseThrow(() -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
        todoRepo.updateStatusById(task.getStatus(), id);
        return BaseSuccessResponse.getSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse patchText(ChangeTextTodoDto task, Long id) {
        todoRepo.findById(id).orElseThrow(() -> new CustomException(ValidationConstants.TASK_NOT_FOUND));
        todoRepo.updateTextById(task.getText(), id);
        return BaseSuccessResponse.getSuccessResponse();
    }

    @Transactional
    public BaseSuccessResponse patch(ChangeStatusTodoDto task) {
        todoRepo.updateStatus(task.getStatus());
        return BaseSuccessResponse.getSuccessResponse();
    }
}

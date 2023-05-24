package com.example.BasicServer.service;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.model.BaseSuccessResponse;
import com.example.BasicServer.model.CustomSuccessResponse;
import com.example.BasicServer.model.GetNewsDto;
import com.example.BasicServer.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Object getPaginated(Integer page, Integer perPage, boolean status){
        return CustomSuccessResponse.getSuccessResponse(GetNewsDto.GetNewsDto(todoRepo.
                findAll(PageRequest.of(page, perPage))
                .getContent()
                .stream()
                .filter(taskEntity -> taskEntity.isStatus() == status)
                .toList()));
    }

    public BaseSuccessResponse delete(Long id) {
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

    public TaskEntity patchStatus(ChangeStatusTodoDto task, Long id) {
        Optional<TaskEntity> taskEntity = todoRepo.findById(id);
        taskEntity.get().setStatus(task.isStatus());
        return todoRepo.save(taskEntity.get());
    }

    public TaskEntity patchText(ChangeTextTodoDto task, Long id) {
        Optional<TaskEntity> taskEntity = todoRepo.findById(id);
        taskEntity.get().setText(task.getText());
        return todoRepo.save(taskEntity.get());
    }

    public void patch(ChangeStatusTodoDto task) {
        for (TaskEntity entity : todoRepo.findAll()
        ) {
            entity.setStatus(task.isStatus());
            todoRepo.save(entity);
        }
    }
}

package com.example.BasicServer.service;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TodoRepo todoRepo;

    public TaskEntity save(CreateTodoDto todo) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setText(todo.getText());
        return todoRepo.save(taskEntity);
    }

    public void getPaginated(Integer perPage, Boolean status){
        List<TaskEntity> taskEntityList;
        taskEntityList = todoRepo.findAll();
    }

    public Long delete(Long id) {
        todoRepo.deleteById(id);
        return id;
    }

    public void deleteAllReady() {
        for (TaskEntity entity : todoRepo.findAll()
        ) {
            if (entity.isStatus()) {
                todoRepo.delete(entity);
            }
        }
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

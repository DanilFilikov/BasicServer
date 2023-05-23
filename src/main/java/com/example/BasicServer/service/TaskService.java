package com.example.BasicServer.service;

import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.entity.TaskEntity;
import com.example.BasicServer.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TodoRepo todoRepo;

    public TaskEntity save(CreateTodoDto todo){

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setText(todo.getText());
        return todoRepo.save(taskEntity);

    }

    /*public TaskEntity getPaginated(){
        int page;
        int perPage;
        boolean status;

    }*/

    public Long delete(Long id){
        todoRepo.deleteById(id);
        return id;
    }

    public void deleteAllReady(){

    }

    public TaskEntity patchStatus(TaskEntity task, Long id){
        Optional<TaskEntity> taskEntity = todoRepo.findById(id);
        taskEntity.get().setStatus(task.isStatus());
        return todoRepo.save(taskEntity.get());
    }
}
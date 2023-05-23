package com.example.BasicServer.controller.todo;

import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.service.TaskService;
import com.example.BasicServer.entity.TaskEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<String> create(@Validated @RequestBody CreateTodoDto todoDto) {
        try {
            taskService.save(todoDto);
            return ResponseEntity.ok("Tasks created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
        try {
            taskService.deleteAllReady();
            return ResponseEntity.ok("Ready tasks deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity patchStatus(@PathVariable Long id, @RequestBody TaskEntity task){
        try {
            taskService.patchStatus(task, id);
            return ResponseEntity.ok("Status is patched");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
package com.example.BasicServer.controller;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.service.TaskService;

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
            taskService.delete(id);
            return ResponseEntity.ok("Task has been deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
        try {
            taskService.deleteAllReady();
            return ResponseEntity.ok("Ready tasks has been deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity patchStatus(@PathVariable Long id, @Validated @RequestBody ChangeStatusTodoDto task) {
        try {
            taskService.patchStatus(task, id);
            return ResponseEntity.ok("Status has been patched");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PatchMapping("/text/{id}")
    public ResponseEntity patchText(@PathVariable Long id, @Validated @RequestBody ChangeTextTodoDto task) {
        try {
            taskService.patchText(task, id);
            return ResponseEntity.ok("Text has been patched");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PatchMapping("/status")
    public ResponseEntity patch(@Validated @RequestBody ChangeStatusTodoDto task) {
        try {
            taskService.patch(task);
            return ResponseEntity.ok("All tasks has been patched");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
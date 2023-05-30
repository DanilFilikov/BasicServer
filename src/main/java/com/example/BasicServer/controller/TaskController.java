package com.example.BasicServer.controller;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.error.ValidationConstants;
import com.example.BasicServer.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateTodoDto todoDto) {
        return ResponseEntity.ok(taskService.createTask(todoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Positive(message = ValidationConstants.ID_MUST_BE_POSITIVE) @PathVariable Long id) {
        return ResponseEntity.ok(taskService.delete(id));
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
       return ResponseEntity.ok(taskService.deleteAllReady());
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity patchStatus(@PathVariable Long id, @Valid @RequestBody ChangeStatusTodoDto task) {
            return ResponseEntity.ok(taskService.patchStatus(task, id));
    }

    @PatchMapping("/text/{id}")
    public ResponseEntity patchText(@PathVariable Long id, @Valid @RequestBody ChangeTextTodoDto task){
        return ResponseEntity.ok(taskService.patchText(task, id));
    }

    @PatchMapping("/status/")
    public ResponseEntity patch(@Valid @RequestBody ChangeStatusTodoDto task) {
            return ResponseEntity.ok(taskService.patch(task));
    }

    @GetMapping
    public ResponseEntity getPaginated(@RequestParam Integer page, @RequestParam Integer perPage, @RequestParam(required = false) boolean status){
            return ResponseEntity.ok(taskService.getPaginated(page, perPage, status));
    }
}
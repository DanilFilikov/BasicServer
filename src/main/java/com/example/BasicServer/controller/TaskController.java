package com.example.BasicServer.controller;

import com.example.BasicServer.dto.ChangeStatusTodoDto;
import com.example.BasicServer.dto.ChangeTextTodoDto;
import com.example.BasicServer.dto.CreateTodoDto;
import com.example.BasicServer.exception.CustomException;
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

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity create(@Validated @RequestBody CreateTodoDto todoDto) {
        try {
            return ResponseEntity.ok(taskService.save(todoDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws CustomException {
        return ResponseEntity.ok(taskService.delete(id));
    }

    @DeleteMapping
    public ResponseEntity deleteAllReady() {
        try {
            return ResponseEntity.ok(taskService.deleteAllReady());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity patchStatus(@PathVariable Long id, @Validated @RequestBody ChangeStatusTodoDto task) throws CustomException {
            return ResponseEntity.ok(taskService.patchStatus(task, id));
    }

    @PatchMapping("/text/{id}")
    public ResponseEntity patchText(@PathVariable Long id, @Validated @RequestBody ChangeTextTodoDto task) throws CustomException{
        return ResponseEntity.ok(taskService.patchText(task, id));
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

    @GetMapping("/{page}/{perPage}")
    public ResponseEntity getPaginated(@PathVariable Integer page, @PathVariable Integer perPage, @RequestParam(required = false) boolean status){
        try {
            return ResponseEntity.ok(taskService.getPaginated(page, perPage, status));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
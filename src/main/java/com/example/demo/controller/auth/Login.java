package com.example.demo.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Login {
    @GetMapping
    public ResponseEntity loginUser() {
        try {
            return ResponseEntity.ok("Server is running");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
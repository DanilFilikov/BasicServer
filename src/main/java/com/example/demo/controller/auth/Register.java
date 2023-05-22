package com.example.demo.controller.auth;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Register {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserEntity user){
        try {
            userRepo.save(user);
            return ResponseEntity.ok("User saved");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
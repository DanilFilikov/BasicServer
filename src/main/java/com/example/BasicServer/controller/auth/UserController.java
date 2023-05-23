package com.example.BasicServer.controller.auth;

import com.example.BasicServer.entity.UserEntity;
import com.example.BasicServer.exception.UserAlreadyExistsException;
import com.example.BasicServer.exception.UserNameCannotBeNullException;
import com.example.BasicServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping
        public ResponseEntity<String> registerUser(@Validated @RequestBody UserEntity user) {
            try {
                userService.registerUser(user);
                return ResponseEntity.ok("User saved");

            } catch (UserAlreadyExistsException | UserNameCannotBeNullException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error");
            }
        }

    @GetMapping
    public ResponseEntity<String> loginUser() {
        try {
            return ResponseEntity.ok("Server is running");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}

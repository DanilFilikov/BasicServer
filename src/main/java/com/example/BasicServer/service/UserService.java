package com.example.BasicServer.service;

import com.example.BasicServer.entity.UserEntity;
import com.example.BasicServer.exception.UserAlreadyExistsException;
import com.example.BasicServer.exception.UserNameCannotBeNullException;
import com.example.BasicServer.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registerUser(UserEntity user) throws UserAlreadyExistsException, UserNameCannotBeNullException {
        if(userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        if(user.getUsername() == null){
            throw new UserNameCannotBeNullException("Имя не может быть пустым");
        }
       return userRepo.save(user);
    }
}
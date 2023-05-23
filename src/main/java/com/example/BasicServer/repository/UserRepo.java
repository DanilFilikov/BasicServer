package com.example.BasicServer.repository;

import com.example.BasicServer.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
        UserEntity findByUsername(String username);
}

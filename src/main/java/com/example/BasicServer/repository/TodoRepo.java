package com.example.BasicServer.repository;

import com.example.BasicServer.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TaskEntity, Long> {

}

package com.example.BasicServer.repository;

import com.example.BasicServer.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface TodoRepo extends JpaRepository<TaskEntity, Long> {
    @Transactional
    @Modifying
    @Query("update TaskEntity t set t.status = :status")
    void updateStatus(@Param("status") boolean status);

}

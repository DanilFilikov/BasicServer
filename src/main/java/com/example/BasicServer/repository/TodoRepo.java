package com.example.BasicServer.repository;

import com.example.BasicServer.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepo extends JpaRepository<TaskEntity, Long> {

    @Modifying
    @Query("update TaskEntity t set t.status = :status")
    void updateStatus(@Param("status") boolean status);

    @Modifying
    @Query("update TaskEntity t set t.text = :text where t.id = :id")
    void updateTextById(@Param("text") String text, @Param("id") Long id);

    @Modifying
    @Query("update TaskEntity t set t.status = :status where t.id = :id")
    void updateStatusById(@Param("status") boolean status, @Param("id") Long id);

    List<TaskEntity> findByStatus(Boolean status);

    void deleteByStatus(boolean status);
}
